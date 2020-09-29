#init auth
#    authProvider = createProvider().setNonces(new JsonArray().add("queiM3ayei1ahCheicupohphioveer0O"));

$vertx.create_http_server().request_handler() { |req|
  if (req.uri().==("/"))
    # Serve the index page
    req.response().send_file("index.html")
  elsif (req.uri().start_with?("/form"))
    req.response().set_chunked(true)
    req.set_expect_multipart(true)
    req.end_handler() { |v|
      req.form_attributes().names().each do |attr|
        req.response().write("Got attr #{attr} : #{req.form_attributes().get(attr)}\n")
      end
      req.response().end()
    }
    =begin
            //paste start
            UsernamePasswordCredentials credentials =
              new UsernamePasswordCredentials(req.formAttributes().get("username"), req.formAttributes().get("password"));

            authProvider.authenticate(credentials,user -> {
              //assertNotNull(user);
              System.out.println("=== got this:\n" + user);
            });
            //paste end
    =end
  else
    req.response().set_status_code(404).end()
  end
}.listen(8080)
