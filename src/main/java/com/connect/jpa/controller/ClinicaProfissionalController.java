package com.connect.jpa.controller;

import com.connect.jpa.model.ClinicaProfissionalModel;
import com.connect.jpa.model.ProfissionalEspecialistaModel;
import com.connect.jpa.service.ClinicaProfissionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinica-profissionais/")
@Tag(name = "Clínica e Profissionais", description = "Gestão de relações entre clínicas e profissionais")
public class ClinicaProfissionalController {

    private final ClinicaProfissionalService clinicaProfissionalService;

    public ClinicaProfissionalController(ClinicaProfissionalService clinicaProfissionalService) {
        this.clinicaProfissionalService = clinicaProfissionalService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Relações", 
        description = "Recupera todas as relações entre clínicas e profissionais cadastrados"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Relações listadas com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhuma relação encontrada")
    })
    public List<ClinicaProfissionalModel> listarTodos() {
        return clinicaProfissionalService.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Relação por ID", 
        description = "Recupera uma relação específica entre clínica e profissional pelo ID"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Relação encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Relação não encontrada")
    })
    public ResponseEntity<ClinicaProfissionalModel> buscarPorId(@PathVariable Long id) {
        return clinicaProfissionalService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    @Operation(
        summary = "Criar Nova Relação", 
        description = "Cadastra uma nova relação entre clínica e profissional"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Relação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public ResponseEntity<ClinicaProfissionalModel> criarRelacao(
            @RequestParam Long clinicaId,
            @RequestBody ProfissionalEspecialistaModel profissional
    ) {
        try {
            ClinicaProfissionalModel clinicaProfissional = clinicaProfissionalService.criarClinicaProfissional(clinicaId, profissional);
            return ResponseEntity.status(201).body(clinicaProfissional);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Relação", 
        description = "Atualiza as informações de uma relação existente entre clínica e profissional"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Relação atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Relação não encontrada")
    })
    public ResponseEntity<ClinicaProfissionalModel> atualizarRelacao(
            @PathVariable Long id,
            @RequestParam Long clinicaId,
            @RequestBody ProfissionalEspecialistaModel profissionalAtualizado
    ) {
        return clinicaProfissionalService.atualizarClinicaProfissional(id, clinicaId, profissionalAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover Relação", 
        description = "Exclui uma relação entre clínica e profissional do sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Relação removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Relação não encontrada")
    })
    public ResponseEntity<?> deletarRelacao(@PathVariable Long id) {
        return clinicaProfissionalService.deletarClinicaProfissional(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
