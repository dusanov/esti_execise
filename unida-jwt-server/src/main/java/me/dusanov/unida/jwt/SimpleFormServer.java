package me.dusanov.unida.jwt;

import me.dusanov.unida.util.Runner;
//import io.vertx.ext.auth.authentication.UsernamePasswordCredentials;
import io.vertx.ext.auth.jdbc.JDBCAuth;
//import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.jwt.JWTOptions;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.http.HttpHeaders;
import me.dusanov.unida.jwt.CREATE_DB_SQL;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 * NOTE! It's recommended to use Vert.x-Web for examples like this
 *
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class SimpleFormServer extends AbstractVerticle {

  private final Logger LOGGER = LoggerFactory.getLogger( SimpleFormServer.class );
  protected JDBCAuth jdbcAuthProvider;
  protected JDBCClient client;
  protected io.vertx.ext.auth.jwt.JWTAuth jwtProvider;

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(SimpleFormServer.class);
  }

  public void createDb() throws Exception {
    //this should probably be something like client.getConfig().getUrl() or getting connection from client !?
    Connection conn = /*this.client.getConnection();*/DriverManager.getConnection("jdbc:hsqldb:mem:test?shutdown=true");
    for (String sql : CREATE_DB_SQL.SQL) {
      LOGGER.info("Executing: "  + sql);
      conn.createStatement().execute(sql);
    }
  }

  public void configz() {

    this.client = JDBCClient.create(vertx, new JsonObject()
                  //.put("url", "jdbc:hsqldb:mem:test?shutdown=true")
                  .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
                  .put("driver_class", "org.hsqldb.jdbcDriver"));

    this.jdbcAuthProvider = JDBCAuth.create(vertx, client).setNonces(new JsonArray().add("queiM3ayei1ahCheicupohphioveer0O"));

    //init auth  // - commented out cus' no wrki
    // jwt jwtProvider
    // https://vertx.io/docs/vertx-auth-jwt/java/#_validating_tokens
    this.jwtProvider = io.vertx.ext.auth.jwt.JWTAuth.create(vertx, new io.vertx.ext.auth.jwt.JWTAuthOptions()
               .addPubSecKey(new io.vertx.ext.auth.PubSecKeyOptions()
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

    router.post("/form").handler(ctx -> {

      try {

        //ctx.response().setChunked(true);
        //ctx.request().setExpectMultipart(true);
        //ctx.response().write("This is a test =====\n");

        JsonObject credentials =
          new JsonObject().put("username",ctx.request().formAttributes().get("username")).put("password", ctx.request().formAttributes().get("password"));

        jdbcAuthProvider.authenticate(credentials,user -> {

          if (user.succeeded()){
            String token = jwtProvider.generateToken(
                        new JsonObject().put("wtf_is_sub",ctx.request().formAttributes().get("username")/*"senor_paolo"*/),
                        new JWTOptions());

            LOGGER.info("=== got this user: " + user.result().principal().getString("username")
                    + "\n=== got this token:\n" + token);

            ctx.response().end("Hello " + user.result().principal().getString("username") + ", your token is:\n" + token);

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
      //read jwt token from auth header
      String authHeader = ctx.request().headers().get(HttpHeaders.AUTHORIZATION);
      LOGGER.info("=== auth header for:" + ctx.request().uri() + " is: " + authHeader);
      ctx.response().end("=== holas amigxs ===");
    });
    // Create a router endpoint for the static content.
    router.route().handler(StaticHandler.create());
    vertx.createHttpServer().requestHandler(router).listen(9090);
  }
}
