package com.copa2026.controller;

import com.copa2026.model.Partida;
import com.copa2026.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso Partida (/api/partidas).
 */
@RestController
@RequestMapping("/api/partidas")
public class PartidaController {

    private final PartidaService service;

    @Autowired
    public PartidaController(PartidaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Partida>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Partida> criar(@Valid @RequestBody Partida partida) {
        Partida salvo = service.criar(partida);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Partida> atualizar(@PathVariable Long id, @Valid @RequestBody Partida partida) {
        return ResponseEntity.ok(service.atualizar(id, partida));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
