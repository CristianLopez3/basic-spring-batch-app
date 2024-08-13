package com.cristian.batch.controller;

import com.cristian.batch.factory.JobFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final JobFactory jobFactory ;

    public BatchController(JobLauncher jobLauncher, JobFactory jobFactory ) {
        this.jobLauncher = jobLauncher;
        this.jobFactory = jobFactory;
    }

    @GetMapping("/runReportJob")
    public String runReportJob(@RequestParam("job")String jobName) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(jobFactory.getJob(jobName), jobParameters);
        return String.format("Job %s has been invoked", jobName);
    }
}
