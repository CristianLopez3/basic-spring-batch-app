package com.cristian.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;


@Configuration
public class JobRunnerConfig {

    private final JobLauncher jobLauncher;
    private final Job sampleJob;

    public JobRunnerConfig(JobLauncher jobLauncher, Job sampleJob) {
        this.jobLauncher = jobLauncher;
        this.sampleJob = sampleJob;
    }

    @Bean
    public CommandLineRunner runJob() {
        System.out.println("""
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================
            =====================================================================================

                STARTING PROCESS
                """);
        return args -> {
            try {
                jobLauncher.run(sampleJob, new JobParametersBuilder()
                        .addLong("startAt", System.currentTimeMillis())
                        .toJobParameters());
            } catch (Exception e) {
                System.err.println("Error occurred while running the job: " + e.getMessage());
            }
        };
    }
}
