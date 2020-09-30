package me.dusanov.unida.jwt;

import java.sql.Connection;
import java.sql.DriverManager;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
//import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.jdbc.JDBCAuth;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
//import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import me.dusanov.unida.util.CREATE_DB_SQL;
import me.dusanov.unida.util.Runner;

/*
 * 
 */
public class JwtTokenProvider extends AbstractVerticle {

  private final Logger LOGGER = LoggerFactory.getLogger( JwtTokenProvider.class );
  private static final String BEARER = "Bearer ";
  
  protected JDBCAuth jdbcAuth;
  protected JDBCClient client;
  protected JWTAuth jwtAuth;

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(JwtTokenProvider.class);
  }

  public void createDb() throws Exception {
    //this should probably be something like client.getConfig().getUrl() or getting connection from client !?
    Connection conn = /*this.client.getConnection();*/DriverManager.getConnection("jdbc:hsqldb:mem:test?shutdown=true");
    for (String sql : CREATE_DB_SQL.SQL) {
      LOGGER.info("Executing: "  + sql);
      conn.createStatement().execute(sql);
    }
  }

  //TODO
  public void configz() {

    this.client = JDBCClient.create(vertx, new JsonObject()
                  //.put("url", "jdbc:hsqldb:mem:test?shutdown=true")
                  .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
                  .put("driver_class", "org.hsqldb.jdbcDriver"));

    this.jdbcAuth = JDBCAuth.create(vertx, client).setNonces(new JsonArray().add("queiM3ayei1ahCheicupohphioveer0O"));

    //init auth  // - commented out cus' no wrki
    // jwt jwtProvider
    // https://vertx.io/docs/vertx-auth-jwt/java/#_validating_tokens
    this.jwtAuth = JWTAuth.create(vertx, new JWTAuthOptions()
               .addPubSecKey(new PubSecKeyOptions()
                 .setAlgorithm("HS256")
                 .setPublicKey("keyboard cat")
                 .setSymmetric(true)));
  }


  @Override
  public void start() throws Exception {
    this.configz();
    this.createDb();

    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    router.get("/").handler(ctx -> {
      ctx.response().sendFile("index.html");
    });

    router.post("/authenticate").handler(ctx -> {

      try {
    	  
    	JsonObject credentials =
          new JsonObject().put("username",ctx.request().formAttributes().get("username")).put("password", ctx.request().formAttributes().get("password"));

        jdbcAuth.authenticate(credentials,user -> {

          if (user.succeeded()){
            String token = jwtAuth.generateToken(
                        new JsonObject().put("username",ctx.request().formAttributes().get("username")/*"senor_paolo"*/),
                        new JWTOptions());

            LOGGER.info("=== got this user: " + user.result().principal().getString("username")
                    + "\n=== got this token:\n" + token);

            ctx.response().headers().add(HttpHeaders.AUTHORIZATION, JwtTokenProvider.BEARER + token);
            ctx.response().end("Hello " + user.result().principal().getString("username"));
            //ctx.reroute("/protected/sshh");

          } else{
            ctx.response().setStatusCode(403).end("loggin failed :(");
          }
        });
      } catch (Exception e){
        LOGGER.fatal("=== FATAL ERR:\n",e);
        throw e;
      }
    });

    router.get("/protected/*").handler(ctx -> {

      String authHeader = ctx.request().headers().get(HttpHeaders.AUTHORIZATION) + "";
      LOGGER.info("=== auth header for:" + ctx.request().uri() + " is: " + authHeader);
      
      jwtAuth.authenticate(new JsonObject().put("jwt", authHeader.replace(JwtTokenProvider.BEARER, "")),result -> {
    	  
    	  if (result.succeeded()) {
    		  LOGGER.info("=== jwt auth passed for:"  + result.result().principal().getString("username"));
    	      ctx.response().end(" holas amigx, " + result.result().principal().getString("username"));
    	  }else /* if (result.failed()) */ {
    		  LOGGER.info("### auth failed for:" + ctx.request().uri() + " is: " + authHeader);
    		  ctx.response().setStatusCode(401).end("=== auth failed");
    	  }
      });
    });
    
    // Create a router endpoint for the static content.
    router.route().handler(StaticHandler.create());
    vertx.createHttpServer().requestHandler(router).listen(9090);
  }
}
