package com.connect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.ClinicaOuHospitalModel;

@Repository
public interface ClinicaOuHospitalRepository extends JpaRepository<ClinicaOuHospitalModel, Long> {
	
    // Interface de repositório que herda JpaRepository, fornecendo métodos CRUD para ClinicaOuHospitalModel.
}
