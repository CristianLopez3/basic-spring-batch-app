package com.cristian.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job generateReportJob;

    public BatchController(JobLauncher jobLauncher, @Qualifier("generateReportJob") Job generateReportJob) {
        this.jobLauncher = jobLauncher;
        this.generateReportJob = generateReportJob;
    }

    @GetMapping("/runReportJob")
    public String runReportJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(generateReportJob, jobParameters);
        return "Report job has been started.";
    }
}
