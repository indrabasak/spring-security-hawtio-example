package com.basaki.config;

import io.hawt.springboot.HawtPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * {@code HawtioConfiguration} configures custom Hawtio login plugin.
 * authentication.
 * <p/>
 *
 * @author Indra Basak
 * @since 12/7/17
 */
@Configuration
public class HawtioConfiguration {

    /**
     * Loading the login plugin. It's used for redirecting to hawtio index.html
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
