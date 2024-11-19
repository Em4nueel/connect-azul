package com.connect.jpa.service;

import org.springframework.stereotype.Service;

import com.connect.jpa.model.RevokedToken;
import com.connect.jpa.repository.RevokedTokenRepository;

@Service
public class LogoutService {

    private final RevokedTokenRepository revokedTokenRepository;

    public LogoutService(RevokedTokenRepository revokedTokenRepository) {
        this.revokedTokenRepository = revokedTokenRepository;
    }

    public void revokeToken(String token) {
        if (!revokedTokenRepository.existsByToken(token)) {
            RevokedToken revokedToken = new RevokedToken();
            revokedToken.setToken(token);
            revokedTokenRepository.save(revokedToken);
        }
    }

    public boolean isTokenRevoked(String token) {
        return revokedTokenRepository.existsByToken(token);
    }
}