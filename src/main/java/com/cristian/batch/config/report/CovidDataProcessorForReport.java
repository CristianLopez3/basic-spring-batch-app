package com.cristian.batch.config.report;

import com.cristian.batch.entity.CovidData;
import com.cristian.batch.entity.CovidReport;
import com.cristian.batch.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CovidDataProcessorForReport implements ItemProcessor<CovidData, CovidReport> {

    private final DataMapper dataMapper;

    @Override
    public CovidReport process(CovidData covidData){
        CovidReport report = new CovidReport();
        report.setDate(covidData.getDate());
        report.setConfirmedCases(covidData.getSumNumberOfConfirmedCovid());
        report.setNewAdmissions(covidData.getSumNoNewAdmissionsCovid());
        report.setDischarges(covidData.getSumNoDischargesCovid());
        report.setNewCases(covidData.getSumNumberOfNewCovidCases());

        return dataMapper.toCovidReport(covidData);
    }
}
