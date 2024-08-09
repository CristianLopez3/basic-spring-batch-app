package com.cristian.batch.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "COVID_DATA")
@Data
public class CovidData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long id;

    @Column(name = "X")
    private String X;

    @Column(name = "Y")
    private String Y;

    @Column(name = "date")
    private String date;

    @Column(name = "sum_number_of_confirmed_covid_19")
    private String sumNumberOfConfirmedCovid;

    @Column(name = "sum_no_new_admissions_covid19_pos")
    private String sumNoNewAdmissionsCovid;

    @Column(name = "sum_no_discharges_covid19_posit")
    private String sumNoDischargesCovid;

    @Column(name = "sum_number_of_new_covid_19_cases_co")
    private String sumNumberOfNewCovidCases;

    // X,Y,
    // OBJECTID,
    // Date,
    // SUM_number_of_confirmed_covid_1,
    // SUM_no_new_admissions_covid19_p,
    // SUM_no_discharges_covid19_posit,
    // SUM_number_of_new_covid_19_cases_co


}
