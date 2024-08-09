package com.cristian.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBatchConfig {

    @Bean
    public Job footballJob(JobRepository jobRepository) {
        return new JobBuilder("footballJob", jobRepository)
                .start(playerLoad())
                .next(gameLoad())
                .next(playerSummarization())
                .build();
    }

}
