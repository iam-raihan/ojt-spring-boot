package com.bjit.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.inMemoryAuthentication().withUser("admin")
                .password("{noop}admin").authorities("ROLE_USER").and().withUser("test")
                .password("{noop}test").authorities("ROLE_USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/employee", "/department").access("hasRole('ROLE_USER')")
                .antMatchers("/", "/**").access("permitAll")
                // end::authorizeRequests[]
                .and().logout().logoutSuccessUrl("/")
                // end::enableLogout[]
                // Make H2-Console non-secured; for debug purposes
                // tag::csrfIgnore[]
                .and().csrf().disable().httpBasic()
                // end::csrfIgnore[]
                // Allow pages to be loaded in frames from the same origin; needed for
                // H2-Console
                // tag::frameOptionsSameOrigin[]
                .and().headers().frameOptions().sameOrigin();
    }

}
