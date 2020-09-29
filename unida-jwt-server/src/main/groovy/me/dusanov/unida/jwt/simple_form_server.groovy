//init auth
//    authProvider = createProvider().setNonces(new JsonArray().add("queiM3ayei1ahCheicupohphioveer0O"));

vertx.createHttpServer().requestHandler({ req ->
  if (req.uri() == "/") {
    // Serve the index page
    req.response().sendFile("index.html")
  } else if (req.uri().startsWith("/form")) {
    req.response().setChunked(true)
    req.setExpectMultipart(true)
    req.endHandler({ v ->
      req.formAttributes().names().each { attr ->
        req.response().write("Got attr ${attr} : ${req.formAttributes().get(attr)}\n")
      }
      req.response().end()
    })
    /*
            //paste start
            UsernamePasswordCredentials credentials =
              new UsernamePasswordCredentials(req.formAttributes().get("username"), req.formAttributes().get("password"));

            authProvider.authenticate(credentials,user -> {
              //assertNotNull(user);
              System.out.println("=== got this:\n" + user);
            });
            //paste end
    */
  } else {
    req.response().setStatusCode(404).end()
  }
}).listen(8080)
