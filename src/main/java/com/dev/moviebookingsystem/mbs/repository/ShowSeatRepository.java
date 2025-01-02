package com.dev.moviebookingsystem.mbs.repository;

import com.dev.moviebookingsystem.mbs.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Integer> {
    List<ShowSeat> findByTicketId(int ticketId);
    List<ShowSeat> findShowSeatsByShowId(int showId);
}
