package com.connect.jpa.controller;

import com.connect.jpa.model.ClinicaOuHospitalModel;
import com.connect.jpa.service.ClinicaOuHospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/clinicas/")
@Tag(name = "Clínicas e Hospitais", description = "Gestão de clínicas e hospitais")
public class ClinicaOuHospitalController {
    private final ClinicaOuHospitalService clinicaOuHospitalService;

    public ClinicaOuHospitalController(ClinicaOuHospitalService clinicaOuHospitalService) {
        this.clinicaOuHospitalService = clinicaOuHospitalService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Clínicas", 
        description = "Recupera todas as clínicas e hospitais cadastrados"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clínicas listadas com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhuma clínica encontrada")
    })
    public List<ClinicaOuHospitalModel> todasClinicas() {
        return clinicaOuHospitalService.todasClinicas();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Clínica por ID", 
        description = "Recupera uma clínica ou hospital específico pelo seu identificador"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clínica encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Clínica não encontrada")
    })
    public ResponseEntity<ClinicaOuHospitalModel> pegarPeloId(@PathVariable Long id) {
        return clinicaOuHospitalService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    @Operation(
        summary = "Criar Nova Clínica", 
        description = "Cadastra uma nova clínica ou hospital no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clínica criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public ClinicaOuHospitalModel criarClinica(@RequestBody ClinicaOuHospitalModel clinica) {
        return clinicaOuHospitalService.criarClinica(clinica);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Clínica", 
        description = "Atualiza as informações de uma clínica ou hospital existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clínica atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Clínica não encontrada")
    })
    public ResponseEntity<ClinicaOuHospitalModel> atualizarClinica(
        @PathVariable Long id, 
        @RequestBody ClinicaOuHospitalModel clinica
    ) {
        return clinicaOuHospitalService.atualizarClinica(id, clinica)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover Clínica", 
        description = "Exclui uma clínica ou hospital do sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Clínica removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Clínica não encontrada")
    })
    public ResponseEntity<?> deletarClinica(@PathVariable Long id) {
        return clinicaOuHospitalService.deletarClinica(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}