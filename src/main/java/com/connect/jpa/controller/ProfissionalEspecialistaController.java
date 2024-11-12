package com.connect.jpa.controller;
import com.connect.jpa.model.ProfissionalEspecialistaModel;
import com.connect.jpa.service.ProfissionalEspecialistaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/profissionais/")
public class ProfissionalEspecialistaController {

    private final ProfissionalEspecialistaService profissionalEspecialistaService;

    public ProfissionalEspecialistaController(ProfissionalEspecialistaService profissionalEspecialistaService) {
        this.profissionalEspecialistaService = profissionalEspecialistaService;
    }

    @GetMapping
    public List<ProfissionalEspecialistaModel> todosProfissionais() {
        return profissionalEspecialistaService.todosProfissionais();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalEspecialistaModel> pegarPeloId(@PathVariable Long id) {
        return profissionalEspecialistaService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/novo")
    public ProfissionalEspecialistaModel criarProfissional(@RequestBody ProfissionalEspecialistaModel profissional) {
        return profissionalEspecialistaService.criarProfissional(profissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalEspecialistaModel> atualizarProfissional(@PathVariable Long id,
                                                                               @RequestBody ProfissionalEspecialistaModel profissional) {
        return profissionalEspecialistaService.atualizarProfissional(id, profissional)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProfissional(@PathVariable Long id) {
        return profissionalEspecialistaService.deletarProfissional(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}