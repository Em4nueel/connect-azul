package com.connect.jpa.service;

import com.connect.jpa.model.PessoaFisicaModel;
import com.connect.jpa.repository.PessoaFisicaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

	private final PessoaFisicaRepository repository;

	public PessoaFisicaService(PessoaFisicaRepository repository) {
		this.repository = repository;
	}

	public List<PessoaFisicaModel> todasPessoas() {
		return repository.findAll();
	}

	public Optional<PessoaFisicaModel> pegarPeloId(long id) {
		return repository.findById(id);
	}

	public PessoaFisicaModel criarPessoa(PessoaFisicaModel pessoa) {
		return repository.save(pessoa);
	}

	public Optional<PessoaFisicaModel> atualizarPessoa(long id, PessoaFisicaModel pessoaAtualizada) {
		return repository.findById(id).map(record -> {
			record.setNomeCompleto(pessoaAtualizada.getNomeCompleto());
			record.setCpf(pessoaAtualizada.getCpf());
			record.setDataNascimento(pessoaAtualizada.getDataNascimento());
			record.setEndereco(pessoaAtualizada.getEndereco());
			return repository.save(record);
		});
	}

	public boolean deletarPessoa(long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
