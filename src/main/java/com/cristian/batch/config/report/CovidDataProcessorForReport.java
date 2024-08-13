package com.cristian.batch.config.report;

import com.cristian.batch.entity.CovidData;
import com.cristian.batch.entity.CovidReport;
import org.springframework.batch.item.ItemProcessor;


public class CovidDataProcessorForReport implements ItemProcessor<CovidData, CovidReport> {

    @Override
    public CovidReport process(CovidData covidData){
        CovidReport report = new CovidReport();
        report.setDate(covidData.getDate());
        report.setConfirmedCases(covidData.getSumNumberOfConfirmedCovid());
        report.setNewAdmissions(covidData.getSumNoNewAdmissionsCovid());
        report.setDischarges(covidData.getSumNoDischargesCovid());
        report.setNewCases(covidData.getSumNumberOfNewCovidCases());

        return report;
    }
}
