package com.basaki.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * {@code MultiSecurityConfiguration} configures basic and form Spring security
 * with in memory authentication.
 * <p/>
 *
 * @author Indra Basak
 * @since 12/11/17
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class MultiSecurityConfiguration {

    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(
                "password").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(
                "password").roles("USER");
    }

    @Configuration
    @Order(1)
    public static class BasicWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher(
                    "/camel/**").authorizeRequests().anyRequest().hasRole(
                    "USER")
                    .and().httpBasic()
                    .and().csrf().disable();
        }
    }

    @Configuration
    public static class FormLoginWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and().formLogin().loginPage("/login")
                    .failureUrl("/login?error")
                    .permitAll()
                    .and().logout().logoutRequestMatcher(
                    new AntPathRequestMatcher(
                            "/hawtio/auth/logout/*"))
                    .logoutSuccessUrl("/login?logout")
                    .and().csrf().disable();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/resources/**");
            web.ignoring().antMatchers("/resources/static/**");
            web.ignoring().antMatchers("/webjars/**");
            web.ignoring().antMatchers("/css/**");
        }
    }
}
