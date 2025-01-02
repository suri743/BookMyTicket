package com.dev.moviebookingsystem.mbs.repository;

import com.dev.moviebookingsystem.mbs.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    List<Seat> findSeatsByAuditoriumId(Integer auditoriumId);
}
