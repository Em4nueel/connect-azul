package com.connect.jpa.controller;
import com.connect.jpa.model.PessoaFisicaModel;
import com.connect.jpa.service.PessoaFisicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/pessoas/")
public class PessoaFisicaController {

    private final PessoaFisicaService pessoaFisicaService;

    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @GetMapping
    public List<PessoaFisicaModel> todasPessoas() {
        return pessoaFisicaService.todasPessoas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisicaModel> pegarPeloId(@PathVariable long id) {
        return pessoaFisicaService.pegarPeloId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/nova")
    public PessoaFisicaModel criarPessoa(@RequestBody PessoaFisicaModel pessoa) {
        return pessoaFisicaService.criarPessoa(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaFisicaModel> atualizarPessoa(@PathVariable long id,
                                                             @RequestBody PessoaFisicaModel pessoa) {
        return pessoaFisicaService.atualizarPessoa(id, pessoa)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPessoa(@PathVariable long id) {
        return pessoaFisicaService.deletarPessoa(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
