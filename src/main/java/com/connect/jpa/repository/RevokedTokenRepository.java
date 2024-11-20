package com.connect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.connect.jpa.model.RevokedToken;



public interface RevokedTokenRepository extends JpaRepository<RevokedToken, Long> {
    boolean existsByToken(String token);
}