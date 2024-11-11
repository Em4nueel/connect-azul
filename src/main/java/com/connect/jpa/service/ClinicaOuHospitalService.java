package com.connect.jpa.service;
import com.connect.jpa.model.ClinicaOuHospitalModel;
import com.connect.jpa.repository.ClinicaOuHospitalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ClinicaOuHospitalService {

    private final ClinicaOuHospitalRepository repository;

    public ClinicaOuHospitalService(ClinicaOuHospitalRepository repository) {
        this.repository = repository;
    }

    public List<ClinicaOuHospitalModel> todasClinicas() {
        return repository.findAll();
    }

    public Optional<ClinicaOuHospitalModel> pegarPeloId(Long id) {
        return repository.findById(id);
    }

    public ClinicaOuHospitalModel criarClinica(ClinicaOuHospitalModel clinica) {
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