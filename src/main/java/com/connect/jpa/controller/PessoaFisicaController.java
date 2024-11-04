package com.connect.jpa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.connect.jpa.model.PessoaFisicaModel;
import com.connect.jpa.repository.PessoaFisicaRepository;

@RestController
@RequestMapping("/api/pessoas/")
public class PessoaFisicaController {

	// Repositório que sera utilizado para acessar os dados de PessoaFisicaModel
	private final PessoaFisicaRepository repository;

	// Construtor para injetar o repositorio no controller
	public PessoaFisicaController(PessoaFisicaRepository repository) {
		this.repository = repository;
	}

	// Método GET para retornar uma lista de todas as pessoas fisicas cadastradas
	@GetMapping
	public List<PessoaFisicaModel> todasPessoas() {
		return repository.findAll();
	}

	// Método GET que busca uma pessoa física pelo ID e retorna, se existir;
	// Se não for encontrada, retorna um status 404
	@GetMapping("/{id}")
	public ResponseEntity<PessoaFisicaModel> pegarPeloId(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	// Método POST que cria uma nova pessoa fisica;
	// O corpo de requisição (JSON) é convertido para o modelo PessoaFisicaModel
	@PostMapping("/nova")
	public PessoaFisicaModel criarPessoa(@RequestBody PessoaFisicaModel pessoa) {
		return repository.save(pessoa);
	}

	// Método PUT para atualizar os dados de uma pessoa física com base no ID;
	// Se a pessoa existirm os dados são atualizados com informações fornecidas
	@PutMapping("/{id}")
	public ResponseEntity<PessoaFisicaModel> atualizarPessoa(@PathVariable long id,
			@RequestBody PessoaFisicaModel pessoa) {
		return repository.findById(id).map(record -> {
			// Atualizando cada campo de registro com os novos valores
			record.setEmail(pessoa.getEmail());
			record.setNomeCompleto(pessoa.getNomeCompleto());
			record.setCpf(pessoa.getCpf());
			record.setData(pessoa.getDataNascimento());
			record.setEndereco(pessoa.getEndereco());
			record.setContato(pessoa.getContato());
			// Salvando o registro atualizado no banco de dados
			PessoaFisicaModel atualizado = repository.save(record);
			return ResponseEntity.ok().body(atualizado);
		}).orElse(ResponseEntity.notFound().build());
	}

	// Método DELETE para remover uma pessoa fisica com base no ID;
	// Se a pessoa for encontrada, é excluida do banco de dados
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarPessoa(@PathVariable long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
