package com.dev.moviebookingsystem.mbs.repository;

import com.dev.moviebookingsystem.mbs.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    List<Show> findShowsByAuditoriumId(Integer auditoriumId);
}
