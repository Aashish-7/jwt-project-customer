package com.jwtproject.auth.dao;

import com.jwtproject.auth.model.JwtResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtResponseRepository extends JpaRepository<JwtResponse, Long> {
}