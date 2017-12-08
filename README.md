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
java -jar target/spring-security-hawtio-example-1.0.0.jar
```

### Usage
You will be redirected to login page when you try to access hawtio home page,
`http://localhost:8080/hawtio/index.html`:

![Login Main page](./img/login-main.png)

Once you are logged in, you will be forwarded to `http://localhost:8080/hawtio/index.html`

![Hawtio Home](./img/hawtio-home.png)

Once you are logged in, you can log out by selecting **user** > **Log out** from dropdown menu
on the right corner.

![Hawtio Logout](./img/hawtio-logout.png)

A modal dialog you will pop up to confirm your selection,

![Hawtio Confirm](./img/hawtio-logout-confirmation.png)

Once you are logged out, you will be taken to Hawtio login page,

![Hawtio Loin](./img/hawtio-login.png)


[travis-badge]: https://travis-ci.org/indrabasak/spring-security-hawtio-example.svg?branch=master
[travis-badge-url]: https://travis-ci.org/indrabasak/spring-security-hawtio-example/