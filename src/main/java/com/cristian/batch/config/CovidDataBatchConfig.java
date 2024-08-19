package com.cristian.batch.config;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.cristian.batch.entity.CovidData;
import com.cristian.batch.repository.CovidDataRepository;

@Configuration
@EnableBatchProcessing
public class CovidDataBatchConfig {

    private final CovidDataRepository covidDataRepository;

    private final FileProperties properties;

    @Autowired
    public CovidDataBatchConfig(CovidDataRepository covidDataRepository, FileProperties properties) {
        this.covidDataRepository = covidDataRepository;
        this.properties = properties;
    }

    @Bean
    public FlatFileItemReader<CovidData> covidDataReader() {
        var itemReader = new FlatFileItemReader<CovidData>();
        itemReader.setResource(new FileSystemResource(properties.getInput()));
        itemReader.setName("CovidData-Reader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        itemReader.setSkippedLinesCallback(line -> System.err.println("Skipped line: " + line));
        return itemReader;
    }

    private LineMapper<CovidData> lineMapper() {
        var lineMapper = new DefaultLineMapper<CovidData>();
        var lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(
                "objectId",
                "date",
                "sumNumberOfConfirmedCovid",
                "sumNoNewAdmissionsCovid",
                "sumNoDischargesCovid",
                "sumNumberOfNewCovidCases");

        var fieldSetMapper = new BeanWrapperFieldSetMapper<CovidData>();
        fieldSetMapper.setTargetType(CovidData.class);

        fieldSetMapper.setCustomEditors(Map.of(
                Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd HH:mm:ssX"), true)));

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        return lineMapper;
    }

    @Bean
    public CovidDataProcessor covidDataProcessor() {
        return new CovidDataProcessor();
    }

    @Bean
    public RepositoryItemWriter<CovidData> covidDataItemWriter() {
        var itemWriter = new RepositoryItemWriter<CovidData>();
        itemWriter.setRepository(covidDataRepository);
        itemWriter.setMethodName("save");
        return itemWriter;
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step-1", jobRepository)
                .<CovidData, CovidData>chunk(10, transactionManager)
                .reader(covidDataReader())
                .processor(covidDataProcessor())
                .writer(covidDataItemWriter())
                .build();
    }

    @Bean(name = "sampleJob")
    public Job sampleJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("Parsing csv to database", jobRepository)
                .start(step)
                .build();
    }
}
