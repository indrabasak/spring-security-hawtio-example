package com.basaki.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * {@code SecurityConfiguration} configures Spring security with in memory
 * authentication.
 * <p/>
 *
 * @author Indra Basak
 * @since 12/7/17
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.inMemoryAuthentication().withUser("user").password(
                "password").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(
                "password").roles("USER");
    }

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
