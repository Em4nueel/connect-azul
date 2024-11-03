package com.connect.jpa.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.connect.jpa.model.ContatoModel;
import com.connect.jpa.repository.ContatoRepository;


@RestController
@RequestMapping("/api/contatos/")
public class ContatoController {
    private ContatoRepository repository;

    ContatoController(ContatoRepository contatoRepository){
        this.repository = contatoRepository;
    }

    @GetMapping
    public List<ContatoModel> todosContatos(){
        return repository.findAll();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<ContatoModel> pegarPeloId(@PathVariable long id) {
        return repository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    public ContatoModel criarContato(@RequestBody ContatoModel contato){
    return repository.save(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoModel> atualizarContato(@PathVariable long id,
                                      @RequestBody ContatoModel contato) {
    return repository.findById(id)
            .map(record -> {
                record.setTelefone(contato.getTelefone());
                record.setSite(contato.getSite());
                record.setRedeSocial(contato.getRedeSocial());
                ContatoModel atualizado = repository.save(record);
                return ResponseEntity.ok().body(atualizado);
            }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarContato(@PathVariable long id) {
        return repository.findById(id)
        .map(record -> {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    
    
}
