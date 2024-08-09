package com.cristian.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cristian.batch.entity.CovidData;

@Repository
public interface CovidDataRepository extends JpaRepository<CovidData, Long> {
    
}
