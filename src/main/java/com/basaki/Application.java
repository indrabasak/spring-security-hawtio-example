package com.basaki;

import io.hawt.springboot.EnableHawtio;
import io.hawt.web.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@code BookApplication} represents the entry point for the Spring
 * boot application example.
 * <p/>
 *
 * @author Indra Basak
 * @since 12/7/17
 */
@SpringBootApplication
@EnableHawtio
@ComponentScan(basePackages = {"com.basaki"})
public class Application {

    public static void main(String[] args) {
        System.setProperty(AuthenticationFilter.HAWTIO_AUTHENTICATION_ENABLED,
                "false");
        SpringApplication.run(Application.class, args);
    }
}
