package com.dev.moviebookingsystem.mbs.repository;

import com.dev.moviebookingsystem.mbs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
