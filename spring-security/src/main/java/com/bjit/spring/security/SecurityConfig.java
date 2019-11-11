package com.bjit.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    public static final String DEF_USERS_BY_USERNAME_QUERY =
            "select username,password,enabled " +
                    "from users " +
                    "where username = ?";
    public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
            "select username,authority " +
                    "from authorities " +
                    "where username = ?";
    public static final String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY =
            "select g.id, g.group_name, ga.authority " +
                    "from groups g, group_members gm, group_authorities ga " +
                    "where gm.username = ? " +
                    "and g.id = ga.group_id " +
                    "and g.id = gm.group_id";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
               // .withDefaultSchema()
                //.withUser(User.withUsername("user")
                //        .password(passwordEncoder().encode("pass"))
               //         .roles("USER"));
        
        
//        .usersByUsernameQuery(
//        		 "select username, password, enabled from Users " +
//        		 "where username=?")
//		 .authoritiesByUsernameQuery(
//        		 "select username, authority from UserAuthorities " +
//        		 "where username=?");

        /*auth.inMemoryAuthentication().withUser("admin")
                .password("{noop}admin").authorities("ROLE_USER").and().withUser("test")
                .password("{noop}test").authorities("ROLE_USER");*/

    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()
                .antMatchers("/employee", "/department").access("hasRole('ROLE_USER')")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        http.csrf()
                .ignoringAntMatchers("/h2-console/**");
        http.headers()
                .frameOptions()
                .sameOrigin();

        
    }

}
