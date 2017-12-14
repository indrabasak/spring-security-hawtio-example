package com.basaki.camel;

import com.basaki.model.MyBean;
import com.basaki.service.CamelService;
import javax.ws.rs.core.MediaType;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * {@code CamelRouter} creates Camel routes.
 * <p/>
 *
 * @author Indra Basak
 * @since 12/12/17
 */
@Configuration
@Component
public class CamelRouter extends RouteBuilder {

    @Value("${server.port}")
    private String serverPort;

    @Value("${camel.api.path}")
    private String contextPath;

    @Override
    public void configure() throws Exception {
        restConfiguration().contextPath(contextPath)
                .port(serverPort)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Camel REST API")
                .apiProperty("api.version", "v1")
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

        createPostRoute();
        createGetRoute();
    }

    private void createPostRoute() {
        rest("/api/").description("Camel REST Service")
                .id("api-route")
                .post("/bean")
                .produces(MediaType.APPLICATION_JSON)
                .consumes(MediaType.APPLICATION_JSON)
                .bindingMode(RestBindingMode.auto)
                .type(MyBean.class)
                .to("direct:postRemoteService");

        from("direct:postRemoteService")
                .routeId("direct-route")
                .tracing()
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MyBean bodyIn = (MyBean) exchange.getIn().getBody();
                        CamelService.doSomething(bodyIn);
                        exchange.getIn().setBody(bodyIn);
                    }
                })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));
    }

    private void createGetRoute() {
        rest("/ping").description("Camel Ping Endpoint")
                .id("ping-route")
                .get()
                .produces(MediaType.APPLICATION_JSON)
                .to("direct:ping");

        from("direct:ping")
                .transform().constant("pong");
    }
}
