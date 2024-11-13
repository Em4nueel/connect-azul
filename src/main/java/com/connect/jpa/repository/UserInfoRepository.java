package com.connect.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.UserInfoModel;

@EnableJpaRepositories(basePackages = "com.connect.jpa.repository")
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoModel, Long> { 
    Optional<UserInfoModel> findByName(String username); 
}