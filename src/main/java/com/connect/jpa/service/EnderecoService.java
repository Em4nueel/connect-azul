package com.connect.jpa.service;

import com.connect.jpa.model.EnderecoModel;
import com.connect.jpa.repository.EnderecoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;


    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.repository = enderecoRepository;
    }

    public List<EnderecoModel> todosEnderecos() {
        return repository.findAll();
    }

    public Optional<EnderecoModel> pegarPeloId(long id) {
        return repository.findById(id);
    }

    public EnderecoModel criarEndereco(EnderecoModel endereco) {
        return repository.save(endereco);
    }

    public Optional<EnderecoModel> atualizarEndereco(long id, EnderecoModel enderecoAtualizado) {
        return repository.findById(id).map(record -> {
            record.setRua(enderecoAtualizado.getRua());
            record.setNumero(enderecoAtualizado.getNumero());
            record.setBairro(enderecoAtualizado.getBairro());
            return repository.save(record);
        });
    }

    public boolean deletarEndereco(long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
