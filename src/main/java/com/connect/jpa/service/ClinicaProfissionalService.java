package com.connect.jpa.service;
import com.connect.jpa.model.ClinicaProfissionalModel;
import com.connect.jpa.repository.ClinicaProfissionalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class ClinicaProfissionalService {

    private final ClinicaProfissionalRepository repository;

    public ClinicaProfissionalService(ClinicaProfissionalRepository repository) {
        this.repository = repository;
    }

    public List<ClinicaProfissionalModel> todosClinicaProfissionais() {
        return repository.findAll();
    }

    public Optional<ClinicaProfissionalModel> pegarPeloId(long id) {
        return repository.findById(id);
    }

    public ClinicaProfissionalModel criarClinicaProfissional(ClinicaProfissionalModel clinicaProfissional) {
        return repository.save(clinicaProfissional);
    }

    public Optional<ClinicaProfissionalModel> atualizarClinicaProfissional(long id, ClinicaProfissionalModel clinicaProfissionalAtualizada) {
        return repository.findById(id).map(record -> {
            record.setClinica(clinicaProfissionalAtualizada.getClinica());
            record.setProfissional(clinicaProfissionalAtualizada.getProfissional());
            return repository.save(record);
        });
    }

    public boolean deletarClinicaProfissional(long id) {
        return repository.findById(id).map(record -> {
            repository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
