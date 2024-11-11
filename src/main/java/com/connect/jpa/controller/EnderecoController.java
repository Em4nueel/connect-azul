package com.connect.jpa.controller;
import com.connect.jpa.model.EnderecoModel;
import com.connect.jpa.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/enderecos/")
public class EnderecoController {

    private final EnderecoService enderecoService;


    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public List<EnderecoModel> todosEnderecos() {
        return enderecoService.todosEnderecos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoModel> pegarPeloId(@PathVariable long id) {
        return enderecoService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    public EnderecoModel criarEndereco(@RequestBody EnderecoModel endereco) {
        return enderecoService.criarEndereco(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoModel> atualizarEndereco(@PathVariable long id,
                                                           @RequestBody EnderecoModel endereco) {
        return enderecoService.atualizarEndereco(id, endereco)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarEndereco(@PathVariable long id) {
        return enderecoService.deletarEndereco(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
