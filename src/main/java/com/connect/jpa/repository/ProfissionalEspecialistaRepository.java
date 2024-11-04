package com.connect.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.connect.jpa.model.ProfissionalEspecialistaModel;

@Repository
public interface ProfissionalEspecialistaRepository extends JpaRepository<ProfissionalEspecialistaModel, Long> {
	
    // Extensão do JpaRepository para operações CRUD automáticas no banco de dados.
    // Não é necessário definir métodos adicionais, pois o JpaRepository já fornece
    // funcionalidades como save, findAll, findById, deleteById, etc.
	
}
