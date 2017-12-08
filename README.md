[![Build Status][travis-badge]][travis-badge-url]

![](./img/hawtio_logo.png)

Spring Security Hawtio Example
=============================================
This is an example of configuring Spring security with Spring Boot and [hawtio](http://hawt.io/).

### Build
To build the JAR, execute the following command from the parent directory:

```
mvn clean install
```

### Run
To run the application fromm command line,

```
java -jar target/springfox-ui-from-json-example-1.0.0.jar
```

### Usage
If you try to access `http://localhost:8080/hawtio/index.html`, you will be
redirected to `http://localhost:8080/login` if you are not logged in.

Once you are logged in, you will be forwarded to `http://localhost:8080/hawtio/index.html`

[travis-badge]: https://travis-ci.org/indrabasak/spring-security-hawtio-example.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/spring-security-hawtio-example/