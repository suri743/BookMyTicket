package com.dev.moviebookingsystem.bmt.repository;

import com.dev.moviebookingsystem.bmt.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findSeatsByAuditoriumId(Integer auditoriumId);
}
