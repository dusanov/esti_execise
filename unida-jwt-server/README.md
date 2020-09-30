# POC vertx-jwt-jdbc 

jwt server for unida project

## Build
```bash
mvn clean compile | package
```

## Run
```bash
vertx run me.dusanov.unida.jwt.JwtServer -cp target/classes/
```
or
```bash
vertx run me.dusanov.unida.jwt.JwtServer -cp target/[packaged-jar-name.jar]
```
or
```bash
 mvn exec:java -Dexec.mainClass="me.dusanov.unida.jwt.JwtServer"
```
