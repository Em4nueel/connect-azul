package com.connect.jpa.service;
import com.connect.jpa.model.ClinicaOuHospitalModel;
import com.connect.jpa.model.Usuario;
import com.connect.jpa.repository.ClinicaOuHospitalRepository;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.Optional;


@Service
public class ClinicaOuHospitalService {

    private final ClinicaOuHospitalRepository repository;
    private final UsuarioService usuarioService;

    public ClinicaOuHospitalService(ClinicaOuHospitalRepository repository, UsuarioService usuarioService) {
        this.repository = repository;
        this.usuarioService = usuarioService;
    }

    public List<ClinicaOuHospitalModel> todasClinicas() {
        return repository.findAll();
    }

    public List<ClinicaOuHospitalModel> buscarPorTermo(String termo) {
        String termoNormalizado = Normalizer.normalize(termo, Normalizer.Form.NFD)
            .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
            .toLowerCase();
    
        return repository.findByNomeNormalizado("%" + termoNormalizado + "%");
    }

    public Optional<ClinicaOuHospitalModel> pegarPeloId(Long id) {
        return repository.findById(id);
    }

    public ClinicaOuHospitalModel criarClinica(ClinicaOuHospitalModel clinica) {
        Usuario usuario = clinica.getUsuario();

        Usuario usuarioCriado = usuarioService.addUser(usuario);

        clinica.setUsuario(usuarioCriado);

        return repository.save(clinica);
    }

    public Optional<ClinicaOuHospitalModel> atualizarClinica(Long id, ClinicaOuHospitalModel clinicaAtualizada) {
        return repository.findById(id).map(record -> {
            record.setNome(clinicaAtualizada.getNome());
            record.setCnpj(clinicaAtualizada.getCnpj());
            record.setContatos(clinicaAtualizada.getContatos());
            record.setEnderecos(clinicaAtualizada.getEnderecos());
            return repository.save(record);
        });
    }

    public boolean deletarClinica(Long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}