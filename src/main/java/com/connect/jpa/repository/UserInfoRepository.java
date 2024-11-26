package com.connect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.UserInfo;

import java.util.Optional; 
  
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> { 
    Optional<UserInfo> findByNome(String username); 
}