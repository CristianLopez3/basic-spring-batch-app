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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long objectId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Integer sumNumberOfConfirmedCovid;


    private Integer sumNoNewAdmissionsCovid;

    private Integer sumNoDischargesCovid;

    private Integer sumNumberOfNewCovidCases;
}
