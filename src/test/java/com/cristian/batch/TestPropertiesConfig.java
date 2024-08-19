package com.cristian.batch;

import com.cristian.batch.config.FileProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestPropertiesConfig {

    @Bean
    public FileProperties properties() {
        FileProperties properties = new FileProperties();
        properties.setInput("src/test/resources/coviddata.csv");
        return properties;
    }

}

