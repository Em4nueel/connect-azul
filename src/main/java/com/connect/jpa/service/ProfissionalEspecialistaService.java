package com.connect.jpa.service;

import com.connect.jpa.model.ProfissionalEspecialistaModel;
import com.connect.jpa.repository.ProfissionalEspecialistaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalEspecialistaService {

	private final ProfissionalEspecialistaRepository repository;

	public ProfissionalEspecialistaService(ProfissionalEspecialistaRepository repository) {
		this.repository = repository;
	}

	public List<ProfissionalEspecialistaModel> todosProfissionais() {
		return repository.findAll();
	}

	public Optional<ProfissionalEspecialistaModel> pegarPeloId(Long id) {
		return repository.findById(id);
	}

	public ProfissionalEspecialistaModel criarProfissional(ProfissionalEspecialistaModel profissional) {
		return repository.save(profissional);
	}

	public Optional<ProfissionalEspecialistaModel> atualizarProfissional(Long id,
			ProfissionalEspecialistaModel profissionalAtualizado) {
		return repository.findById(id).map(record -> {
			record.setEspecialista(profissionalAtualizado.getEspecialista());
			record.setIdadePaciente(profissionalAtualizado.getIdadePaciente());
			record.setExperiencia(profissionalAtualizado.getExperiencia());
			return repository.save(record);
		});
	}

	public boolean deletarProfissional(Long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return true;
		}).orElse(false);
	}
}
