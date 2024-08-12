package com.cristian.batch.config.report;

import com.cristian.batch.entity.CovidData;
import com.cristian.batch.entity.CovidReport;

import com.cristian.batch.repository.CovidDataRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class ReportJob {

    @Bean
    public CovidDataProcessorForReport covidDataProcessorForReport() {
        return new CovidDataProcessorForReport();
    }

    @Bean
    public CovidDataItemReader covidDataItemReader(CovidDataRepository covidDataRepository) {
        return new CovidDataItemReader(covidDataRepository);
    }

    @Bean(name = "generateReportJob")
    public Job generateReportJob(JobRepository jobRepository, @Qualifier("generateReportStep") Step step) {
        return new JobBuilder("generating report in pdf", jobRepository)
                .start(step)
                .build();
    }

    @Bean(name = "generateReportStep")
    public Step generateReportStep(
            JobRepository jobRepository,
            CovidDataRepository covidDataRepository,
            PlatformTransactionManager transactionManager) {
        return new StepBuilder("step-1", jobRepository)
                .<CovidData, CovidReport>chunk(100, transactionManager)
                .reader(covidDataItemReader(covidDataRepository))
                .processor(covidDataProcessorForReport())
                .writer(pdfReportWriter())
                .build();
    }

    @Bean
    public PdfReportWriter pdfReportWriter() {
        return new PdfReportWriter();
    }
}