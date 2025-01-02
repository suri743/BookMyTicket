package com.dev.moviebookingsystem.mbs.repository;

import com.dev.moviebookingsystem.mbs.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
