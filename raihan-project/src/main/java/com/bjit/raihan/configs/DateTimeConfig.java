package com.bjit.raihan.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class DateTimeConfig {

    @Bean
    public ObjectMapper objectMapper() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Dhaka")); //default

        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        return mapper;
    }
}
