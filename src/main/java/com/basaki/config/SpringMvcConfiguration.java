package com.basaki.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpringMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private ApplicationContext context;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/hawtio/auth/login/*").setViewName("login");
        //registry.addViewController("/app/core/html/login.html").setViewName("login");
        //registry.addViewController("/hawtio/index.html#/login").setViewName(
        //        "login");
        ///hawtio/auth/login/*
    }
}
