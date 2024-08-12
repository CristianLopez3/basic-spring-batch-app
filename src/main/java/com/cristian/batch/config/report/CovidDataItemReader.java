package com.cristian.batch.config.report;

import com.cristian.batch.entity.CovidData;
import com.cristian.batch.repository.CovidDataRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;

public class CovidDataItemReader implements ItemReader<CovidData> {

    private final CovidDataRepository covidDataRepository;
    private Iterator<CovidData> covidDataIterator;

    @Autowired
    public CovidDataItemReader(CovidDataRepository covidDataRepository) {
        this.covidDataRepository = covidDataRepository;
    }

    @Override
    public CovidData read() {
        if (covidDataIterator == null) {
            covidDataIterator = covidDataRepository.findAll().iterator();
        }
        return covidDataIterator.hasNext() ? covidDataIterator.next() : null;
    }
}