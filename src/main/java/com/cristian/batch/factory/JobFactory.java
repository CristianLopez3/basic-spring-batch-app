package com.cristian.batch.factory;


import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JobFactory {

    private final Job sampleJob;
    private final Job generateReportJob;

    public JobFactory(@Qualifier("sampleJob") Job sampleJob,
                      @Qualifier("generateReportJob") Job generateReportJob) {
        this.sampleJob = sampleJob;
        this.generateReportJob = generateReportJob;
    }

    public Job getJob(String jobName) {
        return switch (jobName) {
            case "sampleJob" -> sampleJob;
            case "generateReportJob" -> generateReportJob;
            default -> throw new IllegalArgumentException("Invalid job name: " + jobName);
        };
    }
}
