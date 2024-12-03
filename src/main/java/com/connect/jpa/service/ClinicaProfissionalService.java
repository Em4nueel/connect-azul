package com.connect.jpa.service;

import com.connect.jpa.model.ClinicaProfissionalModel;
import com.connect.jpa.model.ProfissionalEspecialistaModel;
import com.connect.jpa.repository.ClinicaProfissionalRepository;
import com.connect.jpa.repository.ClinicaOuHospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicaProfissionalService {

    private final ClinicaProfissionalRepository clinicaProfissionalRepository;
    private final ClinicaOuHospitalRepository clinicaOuHospitalRepository;

    public ClinicaProfissionalService(ClinicaProfissionalRepository clinicaProfissionalRepository,
                                      ClinicaOuHospitalRepository clinicaOuHospitalRepository) {
        this.clinicaProfissionalRepository = clinicaProfissionalRepository;
        this.clinicaOuHospitalRepository = clinicaOuHospitalRepository;
    }

    public List<ClinicaProfissionalModel> listarTodos() {
        return clinicaProfissionalRepository.findAll();
    }

    public List<ClinicaProfissionalModel> listarPorClinicaId(Long clinicaId) {
        return clinicaProfissionalRepository.findByClinicaId(clinicaId);
    }

    public Optional<ClinicaProfissionalModel> pegarPeloId(Long id) {
        return clinicaProfissionalRepository.findById(id);
    }

    public ClinicaProfissionalModel criarClinicaProfissional(Long clinicaId, ProfissionalEspecialistaModel profissional) {
        // Verifica se a clínica existe
        clinicaOuHospitalRepository.findById(clinicaId)
                .orElseThrow(() -> new IllegalArgumentException("Clínica com ID " + clinicaId + " não encontrada."));

        // Cria a relação
        ClinicaProfissionalModel clinicaProfissional = new ClinicaProfissionalModel();
        clinicaProfissional.setClinicaId(clinicaId);
        clinicaProfissional.setProfissional(profissional);

        return clinicaProfissionalRepository.save(clinicaProfissional);
    }

    public Optional<ClinicaProfissionalModel> atualizarClinicaProfissional(Long id, Long clinicaId, ProfissionalEspecialistaModel profissionalAtualizado) {
        return clinicaProfissionalRepository.findById(id).map(record -> {
            // Atualiza o ID da clínica e os dados do profissional
            clinicaOuHospitalRepository.findById(clinicaId)
                    .orElseThrow(() -> new IllegalArgumentException("Clínica com ID " + clinicaId + " não encontrada."));
            record.setClinicaId(clinicaId);
            record.setProfissional(profissionalAtualizado);
            return clinicaProfissionalRepository.save(record);
        });
    }

    public boolean deletarClinicaProfissional(Long id) {
        return clinicaProfissionalRepository.findById(id).map(record -> {
            clinicaProfissionalRepository.deleteById(id);
            return true;
        }).orElse(false);
    }
}
