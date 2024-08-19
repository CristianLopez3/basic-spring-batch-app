package com.cristian.batch.mapper;

import com.cristian.batch.entity.CovidData;
import com.cristian.batch.entity.CovidReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper {


    CovidReport toCovidReport(CovidData covidData);

    CovidData toCovidData(CovidReport covidReport);

}
