package com.copa2026.controller;

import com.copa2026.model.Jogador;
import com.copa2026.service.JogadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso Jogador.
 * GET    /api/jogadores              -> lista todos (ou filtra via ?busca=texto)
 * GET    /api/jogadores/{id}         -> busca por id
 * POST   /api/jogadores              -> cria
 * PUT    /api/jogadores/{id}         -> edita
 * DELETE /api/jogadores/{id}         -> exclui
 */
@RestController
@RequestMapping("/api/jogadores")
public class JogadorController {

    private final JogadorService service;

    @Autowired
    public JogadorController(JogadorService service) {
        this.service = service;
    }

    // Se o parâmetro "busca" for informado, filtra por nome ou país.
    // Caso contrário, lista todos os jogadores. Usado pelo campo de busca do frontend.
    @GetMapping
    public ResponseEntity<List<Jogador>> listarTodos(@RequestParam(value = "busca", required = false) String busca) {
        if (busca != null && !busca.isBlank()) {
            return ResponseEntity.ok(service.buscar(busca));
        }
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogador> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Jogador> criar(@Valid @RequestBody Jogador jogador) {
        Jogador salvo = service.criar(jogador);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizar(@PathVariable Long id, @Valid @RequestBody Jogador jogador) {
        return ResponseEntity.ok(service.atualizar(id, jogador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
