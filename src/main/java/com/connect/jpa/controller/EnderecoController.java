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

import com.connect.jpa.model.EnderecoModel;
import com.connect.jpa.repository.EnderecoRepository;

@RestController
@RequestMapping("/api/enderecos/")
public class EnderecoController {
	private final EnderecoRepository repository;

	// Injeção de dependência do repositório EnderecoRepository
	public EnderecoController(EnderecoRepository enderecoRepository) {
		this.repository = enderecoRepository;
	}

	// Endpoint para listar todos os endereços cadastrados no banco de dados
	@GetMapping
	public List<EnderecoModel> todosEnderecos() {
		return repository.findAll();
	}

	// Endpoint para buscar um endereõ pelo ID. Se o endereço for encontrado,
	// ele será retornado com status 200; caso contrário, retorna status 404
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoModel> pegarPeloId(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// EndPoint para criar um novo endereço. Recebe um objeto JSON no corpo da
	// requisição, que é convertido para um EnderecoModel e salvo no banco
	@PostMapping("/novo")
	public EnderecoModel criarEndereco(@RequestBody EnderecoModel endereco) {
		return repository.save(endereco);
	}

	// Endpoint para atualizar um endereço existente com base no ID
	// Verifica se o endereço existe e, se encontrado, atualiza as propriedades.
	// Retorna o objeto atualizado ou um status 404 se não encontrado
	@PutMapping("/{id}")
	public ResponseEntity<EnderecoModel> atualizarEndereco(@PathVariable long id, @RequestBody EnderecoModel endereco) {
		return repository.findById(id).map(record -> {
			record.setRua(endereco.getRua());
			record.setNumero(endereco.getNumero());
			record.setBairro(endereco.getBairro());
			EnderecoModel atualizado = repository.save(record);
			return ResponseEntity.ok().body(atualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Endpoint para excluir um endereço pelo ID. Verifica se o encereço existe
	// e o deleta caso encontrado, retornando status 200. Caso contrário, retorna
	// 404
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarEndereco(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
