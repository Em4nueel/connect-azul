package com.connect.jpa.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.connect.jpa.model.ClinicaProfissionalModel;


@Repository
public interface ClinicaProfissionalRepository extends JpaRepository<ClinicaProfissionalModel, Long> {

}