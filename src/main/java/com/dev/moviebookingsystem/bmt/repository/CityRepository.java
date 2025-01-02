package com.dev.moviebookingsystem.bmt.repository;

import com.dev.moviebookingsystem.bmt.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
