package com.connect.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.ClinicaOuHospitalModel;

@Repository
public interface ClinicaOuHospitalRepository extends JpaRepository<ClinicaOuHospitalModel, Long> {
    List<ClinicaOuHospitalModel> findByNomeContainingIgnoreCase(String nome);
}
