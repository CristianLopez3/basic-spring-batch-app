package com.cristian.batch.entity;

import jakarta.persistence.*;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "COVID_DATA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CovidData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "object_id")
    private Long objectId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "sum_number_of_confirmed_covid")
    private Integer sumNumberOfConfirmedCovid;

    @Column(name = "sum_no_new_admissions_covid")
    private Integer sumNoNewAdmissionsCovid;

    @Column(name = "sum_no_discharges_covid")
    private Integer sumNoDischargesCovid;

    @Column(name = "sum_number_of_new_covid_cases")
    private Integer sumNumberOfNewCovidCases;
}
