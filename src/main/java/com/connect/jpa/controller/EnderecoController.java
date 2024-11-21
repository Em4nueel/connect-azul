package com.connect.jpa.controller;

import com.connect.jpa.model.EnderecoModel;
import com.connect.jpa.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/api/enderecos/")
@Tag(name = "Endereços", description = "Gestão de endereços")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Endereços", 
        description = "Recupera todos os endereços cadastrados"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereços listados com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhum endereço encontrado")
    })
    public List<EnderecoModel> todosEnderecos() {
        return enderecoService.todosEnderecos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Endereço por ID", 
        description = "Recupera um endereço específico pelo seu identificador"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    public ResponseEntity<EnderecoModel> pegarPeloId(@PathVariable long id) {
        return enderecoService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    @Operation(
        summary = "Criar Novo Endereço", 
        description = "Cadastra um novo endereço no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public EnderecoModel criarEndereco(@RequestBody EnderecoModel endereco) {
        return enderecoService.criarEndereco(endereco);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Endereço", 
        description = "Atualiza as informações de um endereço existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    public ResponseEntity<EnderecoModel> atualizarEndereco(
        @PathVariable long id, 
        @RequestBody EnderecoModel endereco
    ) {
        return enderecoService.atualizarEndereco(id, endereco)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover Endereço", 
        description = "Exclui um endereço do sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Endereço não encontrado")
    })
    public ResponseEntity<?> deletarEndereco(@PathVariable long id) {
        return enderecoService.deletarEndereco(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}