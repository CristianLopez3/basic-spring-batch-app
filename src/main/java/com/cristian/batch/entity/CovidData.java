package com.cristian.batch.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "COVID_DATA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CovidData {

    @Column(name = "X")
    private String x; // Renombrado de X a x

    @Column(name = "Y")
    private String y; // Renombrado de Y a y

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "object_id")
    private Long id;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "SUM_number_of_confirmed_covid_1")
    private Integer sumNumberOfConfirmedCovid;

    @Column(name = "SUM_no_new_admissions_covid19_p")
    private Integer sumNoNewAdmissionsCovid;

    @Column(name = "SUM_no_discharges_covid19_posit")
    private Integer sumNoDischargesCovid;

    @Column(name = "SUM_number_of_new_covid_19_cases_co")
    private Integer sumNumberOfNewCovidCases;
}
