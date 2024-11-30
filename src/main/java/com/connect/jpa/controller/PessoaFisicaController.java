package com.connect.jpa.controller;

import com.connect.jpa.model.PacienteModel;
import com.connect.jpa.service.PessoaFisicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas/")
@Tag(name = "Pessoas Físicas", description = "Gestão de pessoas físicas")
public class PessoaFisicaController {

    private final PessoaFisicaService pessoaFisicaService;

    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Pessoas Físicas", 
        description = "Recupera todas as pessoas físicas cadastradas"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pessoas físicas listadas com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhuma pessoa física encontrada")
    })
    public List<PacienteModel> todasPessoas() {
        return pessoaFisicaService.todasPessoas();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Pessoa Física por ID", 
        description = "Recupera uma pessoa física específica pelo seu identificador"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pessoa física encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa física não encontrada")
    })
    public ResponseEntity<PacienteModel> pegarPeloId(@PathVariable long id) {
        return pessoaFisicaService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/nova")
    @Operation(
        summary = "Criar Nova Pessoa Física", 
        description = "Cadastra uma nova pessoa física no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pessoa física criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public PacienteModel criarPessoa(@RequestBody PacienteModel pessoa) {
        return pessoaFisicaService.criarPessoa(pessoa);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Pessoa Física", 
        description = "Atualiza as informações de uma pessoa física existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pessoa física atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa física não encontrada")
    })
    public ResponseEntity<PacienteModel> atualizarPessoa(
        @PathVariable long id, 
        @RequestBody PacienteModel pessoa
    ) {
        return pessoaFisicaService.atualizarPessoa(id, pessoa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover Pessoa Física", 
        description = "Exclui uma pessoa física do sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Pessoa física removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Pessoa física não encontrada")
    })
    public ResponseEntity<?> deletarPessoa(@PathVariable long id) {
        return pessoaFisicaService.deletarPessoa(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}