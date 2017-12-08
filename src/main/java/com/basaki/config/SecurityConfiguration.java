package com.basaki.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.inMemoryAuthentication().withUser("user").password(
                "password").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        http.authorizeRequests().antMatchers("/",
                "/hawtio").permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .failureUrl("/login?error")
/*                .successHandler(
                        (request, response, authentication) -> {
                            redirectStrategy.sendRedirect(
                                    request, response,
                                    "/hawtio/index.html");
                        })*/
                .permitAll()
                .and().logout().logoutRequestMatcher(
                new AntPathRequestMatcher(
                        "/hawtio/auth/logout/*"))
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();
    }
}
