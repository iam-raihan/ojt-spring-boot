package com.bjit.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.bjit.thymeleaf.error.MailConfiguration;
import com.bjit.thymeleaf.error.MailConfigurationDev;
import com.bjit.thymeleaf.error.MailConfigurationProd;

@SpringBootApplication
public class ThymeLeafApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThymeLeafApplication.class, args);
	}

	@Profile("production")
	@Bean
	public MailConfiguration getProdDatabaseConfiguration() {
		return new MailConfigurationProd();
	}

	@Profile("developer")
    @Bean
    public MailConfiguration getDevDatabaseConfiguration() {
        return new MailConfigurationDev();
	}

}
