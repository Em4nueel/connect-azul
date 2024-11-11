package com.connect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.PessoaFisicaModel;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaModel, Long> {
	
}