package com.copa2026.controller;

import com.copa2026.model.DesempenhoJogador;
import com.copa2026.service.DesempenhoJogadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso "melhor desempenho por jogador".
 * Mapeado em /api/jogadores/desempenho (caminho literal, resolvido com prioridade
 * sobre /api/jogadores/{id} pelo Spring MVC).
 */
@RestController
@RequestMapping("/api/jogadores/desempenho")
public class DesempenhoJogadorController {

    private final DesempenhoJogadorService service;

    @Autowired
    public DesempenhoJogadorController(DesempenhoJogadorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DesempenhoJogador>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesempenhoJogador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DesempenhoJogador> criar(@Valid @RequestBody DesempenhoJogador desempenho) {
        DesempenhoJogador salvo = service.criar(desempenho);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DesempenhoJogador> atualizar(@PathVariable Long id, @Valid @RequestBody DesempenhoJogador desempenho) {
        return ResponseEntity.ok(service.atualizar(id, desempenho));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
