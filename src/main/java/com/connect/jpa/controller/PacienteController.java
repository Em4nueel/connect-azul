package com.connect.jpa.controller;

import com.connect.jpa.model.PacienteModel;
import com.connect.jpa.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes/")
@Tag(name = "Pacientes", description = "Gestão de pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Pacientes", 
        description = "Recupera todos pacientes cadastradas"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pacientes listados com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhum Paciente encontrado")
    })
    public List<PacienteModel> todosPacientes() {
        return pacienteService.todosPacientes();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Paciente por ID", 
        description = "Recupera um Paciente específico pelo seu identificador"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paciente encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrada")
    })
    public ResponseEntity<PacienteModel> pegarPeloId(@PathVariable long id) {
        return pacienteService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    @Operation(
        summary = "Criar Novo Usuário Paciente", 
        description = "Cadastra uma novo Paciente no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paciente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public PacienteModel criarPaciente(@RequestBody PacienteModel paciente) {
        return pacienteService.criarPaciente(paciente);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Paciente", 
        description = "Atualiza as informações de um Paciente existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paciente atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    public ResponseEntity<PacienteModel> atualizarPaciente(
        @PathVariable long id, 
        @RequestBody PacienteModel paciente
    ) {
        return pacienteService.atualizarPaciente(id, paciente)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover Paciente", 
        description = "Exclui uma Paciente do sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paciente removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    public ResponseEntity<?> deletarPessoa(@PathVariable long id) {
        return pacienteService.deletarPaciente(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}