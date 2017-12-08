package com.basaki.config;

import io.hawt.springboot.HawtPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HawtioConfiguration {

    /**
     * Loading the login plugin. It's used for redirecting to hawtio inde.html
     * after login.
     */
    @Bean
    public HawtPlugin samplePlugin() {
        return new HawtPlugin("login-plugin",
                "/hawtio/plugins",
                "",
                new String[]{"plugin/js/login-plugin.js"});
    }
}
