package com.dev.moviebookingsystem.bmt.repository;

import com.dev.moviebookingsystem.bmt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
