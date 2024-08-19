package com.cristian.batch;


import com.cristian.batch.config.CovidDataBatchConfig;
import com.cristian.batch.repository.CovidDataRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBatchTest
@SpringJUnitConfig(classes = {BatchTestConfiguration.class, CovidDataBatchConfig.class, TestPropertiesConfig.class})
@ActiveProfiles("test")
class BatchApplicationTests {


	private final String EXPECTED_JOB_NAME = "Parsing csv to database";
	private final int EXPECTED_DATA_SIZE = 4;

	private JobLauncherTestUtils jobLauncherTestUtils;
	private JobRepositoryTestUtils jobRepositoryTestUtils;


	@Autowired
	public BatchApplicationTests(JobLauncherTestUtils jobLauncherTestUtils, JobRepositoryTestUtils jobRepositoryTestUtils) {
		this.jobRepositoryTestUtils = jobRepositoryTestUtils;
		this.jobLauncherTestUtils = jobLauncherTestUtils;
	}

	@AfterEach
	public void cleanUp() {
		jobRepositoryTestUtils.removeJobExecutions();
	}

	private JobParameters defaultJobParameters() {
		return new JobParametersBuilder()
				.addString("input.file", "src/test/resources/coviddata.csv")
				.addDate("timestamp", Calendar.getInstance().getTime())
				.toJobParameters();
	}

	@Test
	void givenCovidDataFlatFile_whenJobExecuted_thenSuccess(@Autowired CovidDataRepository covidDataRepository) throws Exception {
		// when
		var jobExecution = jobLauncherTestUtils.launchJob(defaultJobParameters());
		var actualJobInstance = jobExecution.getJobInstance();
		var actualJobExitStatus = jobExecution.getExitStatus();

		// then
		assertThat(actualJobInstance.getJobName()).isEqualTo(EXPECTED_JOB_NAME);
		assertThat(actualJobExitStatus.getExitCode()).isEqualTo(ExitStatus.COMPLETED.getExitCode());
		assertThat(covidDataRepository.findAll()).hasSize(EXPECTED_DATA_SIZE);
	}
}

