package com.cristian.batch.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CovidReport {

    private Date date;
    private Integer confirmedCases;
    private Integer newAdmissions;
    private Integer discharges;
    private Integer newCases;

}
