package com.connect.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.ClinicaOuHospitalModel;

@Repository
public interface ClinicaOuHospitalRepository extends JpaRepository<ClinicaOuHospitalModel, Long> {
    @Query("SELECT c FROM ClinicaOuHospital c " +
       "WHERE LOWER(UNACCENT(c.nome)) LIKE LOWER(UNACCENT(:termo))")
    List<ClinicaOuHospitalModel> findByNomeNormalizado(@Param("termo") String termo);
}
