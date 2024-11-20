package com.connect.jpa.controller;

import com.connect.jpa.model.ProfissionalEspecialistaModel;
import com.connect.jpa.service.ProfissionalEspecialistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/profissionais/")
@Tag(name = "Profissionais Especialistas", description = "Gestão de profissionais especialistas")
public class ProfissionalEspecialistaController {

    private final ProfissionalEspecialistaService profissionalEspecialistaService;

    public ProfissionalEspecialistaController(ProfissionalEspecialistaService profissionalEspecialistaService) {
        this.profissionalEspecialistaService = profissionalEspecialistaService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Profissionais Especialistas", 
        description = "Recupera todos os profissionais especialistas cadastrados"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profissionais listados com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhum profissional encontrado")
    })
    public List<ProfissionalEspecialistaModel> todosProfissionais() {
        return profissionalEspecialistaService.todosProfissionais();
    }

    @GetMapping("{clinica_id}/{id}")
    @Operation(
        summary = "Buscar Profissional Especialista por ID", 
        description = "Recupera um profissional especialista específico pelo seu identificador e clínica"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profissional encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<ProfissionalEspecialistaModel> pegarPeloId(
        @PathVariable Long id, 
        @PathVariable long clinica_id
    ) {
        return profissionalEspecialistaService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    @Operation(
        summary = "Criar Novo Profissional Especialista", 
        description = "Cadastra um novo profissional especialista no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profissional criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public ProfissionalEspecialistaModel criarProfissional(@RequestBody ProfissionalEspecialistaModel profissional) {
        return profissionalEspecialistaService.criarProfissional(profissional);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Profissional Especialista", 
        description = "Atualiza as informações de um profissional especialista existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profissional atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<ProfissionalEspecialistaModel> atualizarProfissional(
        @PathVariable Long id, 
        @RequestBody ProfissionalEspecialistaModel profissional
    ) {
        return profissionalEspecialistaService.atualizarProfissional(id, profissional)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover Profissional Especialista", 
        description = "Exclui um profissional especialista do sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profissional removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Profissional não encontrado")
    })
    public ResponseEntity<?> deletarProfissional(@PathVariable Long id) {
        return profissionalEspecialistaService.deletarProfissional(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}