package com.connect.jpa.service;
import com.connect.jpa.model.PacienteModel;
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

    public List<PacienteModel> todasPessoas() {
        return repository.findAll();
    }

    public Optional<PacienteModel> pegarPeloId(long id) {
        return repository.findById(id);
    }

    public PacienteModel criarPessoa(PacienteModel pessoa) {
        return repository.save(pessoa);
    }

    public Optional<PacienteModel> atualizarPessoa(long id, PacienteModel pessoaAtualizada) {
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
