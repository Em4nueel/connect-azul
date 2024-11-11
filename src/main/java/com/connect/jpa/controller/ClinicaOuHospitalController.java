package com.connect.jpa.controller;
import com.connect.jpa.model.ClinicaOuHospitalModel;
import com.connect.jpa.service.ClinicaOuHospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/clinicas/")
public class ClinicaOuHospitalController {

    private final ClinicaOuHospitalService clinicaOuHospitalService;

    public ClinicaOuHospitalController(ClinicaOuHospitalService clinicaOuHospitalService) {
        this.clinicaOuHospitalService = clinicaOuHospitalService;
    }

    @GetMapping
    public List<ClinicaOuHospitalModel> todasClinicas() {
        return clinicaOuHospitalService.todasClinicas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaOuHospitalModel> pegarPeloId(@PathVariable Long id) {
        return clinicaOuHospitalService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    public ClinicaOuHospitalModel criarClinica(@RequestBody ClinicaOuHospitalModel clinica) {
        return clinicaOuHospitalService.criarClinica(clinica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicaOuHospitalModel> atualizarClinica(@PathVariable Long id,
                                                                   @RequestBody ClinicaOuHospitalModel clinica) {
        return clinicaOuHospitalService.atualizarClinica(id, clinica)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarClinica(@PathVariable Long id) {
        return clinicaOuHospitalService.deletarClinica(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}