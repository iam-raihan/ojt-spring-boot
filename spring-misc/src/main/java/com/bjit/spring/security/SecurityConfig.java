package com.bjit.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.userDetailsService(userDetailsService)
        /*auth.jdbcAuthentication().dataSource(dataSource)
				
				 .withDefaultSchema() .withUser(User.withUsername("user1")
				  .password(passwordEncoder().encode("user1")) .roles("EMPLOYEE"))
				  .withUser(User.withUsername("user2")
				  .password(passwordEncoder().encode("user2")) .roles("DEPARTMENT"))
				  .withUser(User.withUsername("admin")
				.password(passwordEncoder().encode("admin")) .roles("ADMIN"))
				 */
        
        
        /*.usersByUsernameQuery(
        		 "select username, password, enabled from Users " +
        		 "where username=?")
		 .authoritiesByUsernameQuery(
        		 "select username, authority from Authorities " +
        		 "where username=?")*/
        
        .passwordEncoder(passwordEncoder());

		/*
		 * auth.inMemoryAuthentication().withUser("user1")
		 * .password("{noop}user1").authorities("ROLE_EMPLOYEE")
		 * .and().withUser("user2")
		 * .password("{noop}user2").authorities("ROLE_DEPARTMENT");
		 */

    }
	
	 @Bean public PasswordEncoder passwordEncoder() { return new
	 BCryptPasswordEncoder(); 
	 }
	 

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/employee").access("hasRole('ROLE_EMPLOYEE')")
                .antMatchers("/department").access("hasRole('ROLE_DEPARTMENT')")
                .antMatchers("/employee","/department").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/register/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin();

        http.csrf()
                .ignoringAntMatchers("/h2-console/**");
        http.headers()
                .frameOptions()
                .sameOrigin();

        
    }

}
