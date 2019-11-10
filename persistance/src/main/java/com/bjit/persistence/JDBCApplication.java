package com.bjit.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.bjit.persistence.error.MailConfiguration;
import com.bjit.persistence.error.MailConfigurationDev;
import com.bjit.persistence.error.MailConfigurationProd;

@SpringBootApplication
public class JDBCApplication {
	public static void main(String[] args) {
		SpringApplication.run(JDBCApplication.class, args);
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
