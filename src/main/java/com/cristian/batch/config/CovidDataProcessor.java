package com.cristian.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.cristian.batch.entity.CovidData;

public class CovidDataProcessor implements ItemProcessor<CovidData, CovidData> {

    @Override
    public CovidData process(CovidData covidData) throws Exception {
        return covidData;
    }   
    
}
