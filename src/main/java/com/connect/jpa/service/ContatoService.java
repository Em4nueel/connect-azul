package com.connect.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.connect.jpa.model.ContatoModel;
import com.connect.jpa.repository.ContatoRepository;

@Service
public class ContatoService {

	private final ContatoRepository repository;

	public ContatoService(ContatoRepository contatoRepository) {
		this.repository = contatoRepository;
	}

	public List<ContatoModel> todosContatos() {
		return repository.findAll();
	}

	public Optional<ContatoModel> pegarPeloId(long id) {
		return repository.findById(id);
	}

	public ContatoModel criarContato(ContatoModel contato) {
		return repository.save(contato);
	}

	public Optional<ContatoModel> atualizarContato(long id, ContatoModel contatoAtualizado) {
		return repository.findById(id).map(record -> {
			record.setTelefone(contatoAtualizado.getTelefone());
			record.setSite(contatoAtualizado.getSite());
			record.setRedeSocial(contatoAtualizado.getRedeSocial());
			return repository.save(record);
		});
	}

	public boolean deletarContato(long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
