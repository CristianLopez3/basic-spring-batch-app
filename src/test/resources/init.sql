
CREATE TABLE COVID_DATA
(
    id                            BIGINT AUTO_INCREMENT PRIMARY KEY,
    object_id                     BIGINT,
    date                          TIMESTAMP,
    sum_number_of_confirmed_covid INT,
    sum_no_new_admissions_covid   INT,
    sum_no_discharges_covid       INT,
    sum_number_of_new_covid_cases INT
);
