package com.dev.moviebookingsystem.bmt.repository;

import com.dev.moviebookingsystem.bmt.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat> findByTicketId(int ticketId);
    List<ShowSeat> findShowSeatsByShowId(int showId);
}
