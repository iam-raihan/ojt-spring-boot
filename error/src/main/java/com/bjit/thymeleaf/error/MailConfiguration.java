package com.bjit.thymeleaf.error;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "mail")
@Data
public class MailConfiguration {
	
	private String hostName;
    private int port;
    private String from;

}
