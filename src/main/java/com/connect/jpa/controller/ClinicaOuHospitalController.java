package com.connect.jpa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.connect.jpa.model.ClinicaOuHospitalModel;
import com.connect.jpa.repository.ClinicaOuHospitalRepository;

@RestController
@RequestMapping("/api/clinicas/")
public class ClinicaOuHospitalController {

	private final ClinicaOuHospitalRepository repository;

	public ClinicaOuHospitalController(ClinicaOuHospitalRepository repository) {
		this.repository = repository;
	}

	// Método GET para listar todas as clínicas e hospitais cadastrados no sistema.
	@GetMapping
	public List<ClinicaOuHospitalModel> todasClinicas() {
		return repository.findAll();
	}

	// Método GET para buscar uma clínica ou hospital pelo ID fornecido.
	@GetMapping("/{id}")
	public ResponseEntity<ClinicaOuHospitalModel> pegarPeloId(@PathVariable Long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// Método POST para cadastrar uma nova clínica ou hospital.
	@PostMapping("/novo")
	public ClinicaOuHospitalModel criarClinica(@RequestBody ClinicaOuHospitalModel clinica) {
		return repository.save(clinica);
	}

	// Método PUT para atualizar informações de uma clínica ou hospital pelo ID.
	@PutMapping("/{id}")
	public ResponseEntity<ClinicaOuHospitalModel> atualizarClinica(@PathVariable Long id,
			@RequestBody ClinicaOuHospitalModel clinica) {
		return repository.findById(id).map(record -> {
			// Atualiza os campos da entidade existente
			record.setNome(clinica.getNome());
			record.setCnpj(clinica.getCnpj());
			record.setContatos(clinica.getContatos());
			record.setEnderecos(clinica.getEnderecos());
			ClinicaOuHospitalModel atualizado = repository.save(record);
			return ResponseEntity.ok().body(atualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Método DELETE para remover uma clínica ou hospital pelo ID.
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarClinica(@PathVariable Long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
