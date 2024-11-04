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

import com.connect.jpa.model.ProfissionalEspecialistaModel;
import com.connect.jpa.repository.ProfissionalEspecialistaRepository;

@RestController
@RequestMapping("/api/profissionais/")
public class ProfissionalEspecialistaController {

	// Repositório para interagir com o banco de dados
	private final ProfissionalEspecialistaRepository repository;

	// Construtor com injeção de dependência do repositório
	public ProfissionalEspecialistaController(ProfissionalEspecialistaRepository repository) {
		this.repository = repository;
	}

	// Método GET para retornar todos os profissionais cadastrados
	@GetMapping
	public List<ProfissionalEspecialistaModel> todosProfissionais() {
		return repository.findAll();
	}

	// Método GET para buscar um profissional pelo ID.
	// Retorna o profissional se encontrado, caso contrário, retorna 404.
	@GetMapping("/{id}")
	public ResponseEntity<ProfissionalEspecialistaModel> pegarPeloId(@PathVariable Long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// Método POST para criar um novo profissional especialista
	@PostMapping("/novo")
	public ProfissionalEspecialistaModel criarProfissional(@RequestBody ProfissionalEspecialistaModel profissional) {
		return repository.save(profissional);
	}

	// Método PUT para atualizar as informações de um profissional especialista
	// com base no ID. Atualiza apenas se o profissional existir.
	@PutMapping("/{id}")
	public ResponseEntity<ProfissionalEspecialistaModel> atualizarProfissional(@PathVariable Long id,
			@RequestBody ProfissionalEspecialistaModel profissional) {
		return repository.findById(id).map(record -> {
			// Atualiza os campos da entidade existente
			record.setEmail(profissional.getEmail());
			record.setEspecialista(profissional.getEspecialista());
			record.setIdadePaciente(profissional.getIdadePaciente());
			record.setExperiencia(profissional.getExperiencia());
			ProfissionalEspecialistaModel atualizado = repository.save(record);
			return ResponseEntity.ok().body(atualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Método DELETE para excluir um profissional especialista pelo ID
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarProfissional(@PathVariable Long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
