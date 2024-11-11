package com.connect.jpa.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.connect.jpa.model.ContatoModel;
import com.connect.jpa.service.ContatoService;
import java.util.List;


@RestController
@RequestMapping("api/contatos/")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @GetMapping
    public List<ContatoModel> todosContatos() {
        return contatoService.todosContatos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoModel> pegarPeloId(@PathVariable long id) {
        return contatoService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    public ContatoModel criarContato(@RequestBody ContatoModel contato) {
        return contatoService.criarContato(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoModel> atualizarContato(@PathVariable long id,
                                                         @RequestBody ContatoModel contato) {
        return contatoService.atualizarContato(id, contato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarContato(@PathVariable long id) {
        return contatoService.deletarContato(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}