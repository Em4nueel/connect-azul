package com.connect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.Usuario;

import java.util.Optional; 
  
@Repository
public interface UserInfoRepository extends JpaRepository<Usuario, Long> { 
    Optional<Usuario> findByNome(String username);

    Optional<Usuario> findByEmail(String email);
}