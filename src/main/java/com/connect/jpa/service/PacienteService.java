package com.connect.jpa.service;
import com.connect.jpa.model.PacienteModel;
import com.connect.jpa.model.Usuario;
import com.connect.jpa.repository.PessoaFisicaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PacienteService {

    private final PessoaFisicaRepository repository;
    private final UsuarioService usuarioService;


    public PacienteService(PessoaFisicaRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    public List<PacienteModel> todosPacientes() {
        return repository.findAll();
    }

    public Optional<PacienteModel> pegarPeloId(long id) {
        return repository.findById(id);
    }

    public PacienteModel criarPaciente(PacienteModel paciente) {
        Usuario usuario = paciente.getUsuario();

        Usuario usuarioCriado = usuarioService.addUser(usuario);

        paciente.setUsuario(usuarioCriado);

        return repository.save(paciente);
    }

    public Optional<PacienteModel> atualizarPaciente(long id, PacienteModel pacienteAtualizado) {
        return repository.findById(id).map(record -> {
            record.setNomeCompleto(pacienteAtualizado.getNomeCompleto());
            record.setCpf(pacienteAtualizado.getCpf());
            record.setDataNascimento(pacienteAtualizado.getDataNascimento());
            record.setEndereco(pacienteAtualizado.getEndereco());
            return repository.save(record);
        });
    }

    public boolean deletarPaciente(long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
