package com.connect.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.ClinicaOuHospitalModel;

@Repository
public interface ClinicaOuHospitalRepository extends JpaRepository<ClinicaOuHospitalModel, Long> {
    @Query("SELECT c FROM ClinicaOuHospitalModel c " +
           "WHERE LOWER(c.nome) LIKE LOWER(CONCAT(:termo, '%'))")
    List<ClinicaOuHospitalModel> findByNomeNormalizado(@Param("termo") String termo);

    Optional<ClinicaOuHospitalModel> findByUsuarioId(Long usuarioId);
}
