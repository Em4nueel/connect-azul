package com.connect.jpa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.connect.jpa.model.ContatoModel;
import com.connect.jpa.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("api/contatos/")
@Tag(name = "Contatos", description = "Gestão de contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    @Operation(
        summary = "Listar Contatos", 
        description = "Recupera todos os contatos cadastrados"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Contatos listados com sucesso"),
        @ApiResponse(responseCode = "204", description = "Nenhum contato encontrado")
    })
    public List<ContatoModel> todosContatos() {
        return contatoService.todosContatos();
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar Contato por ID", 
        description = "Recupera um contato específico pelo seu identificador"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Contato encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<ContatoModel> pegarPeloId(@PathVariable long id) {
        return contatoService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    @Operation(
        summary = "Criar Novo Contato", 
        description = "Cadastra um novo contato no sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Contato criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para criação")
    })
    public ContatoModel criarContato(@RequestBody ContatoModel contato) {
        return contatoService.criarContato(contato);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar Contato", 
        description = "Atualiza as informações de um contato existente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<ContatoModel> atualizarContato(
        @PathVariable long id, 
        @RequestBody ContatoModel contato
    ) {
        return contatoService.atualizarContato(id, contato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Remover Contato", 
        description = "Exclui um contato do sistema"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Contato removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<?> deletarContato(@PathVariable long id) {
        return contatoService.deletarContato(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}