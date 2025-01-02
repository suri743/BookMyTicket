package com.dev.moviebookingsystem.bmt.repository;

import com.dev.moviebookingsystem.bmt.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    List<Theater> findTheatersByCityId(Integer cityId);
}
