package com.copa2026.controller;

import com.copa2026.model.Musica;
import com.copa2026.service.MusicaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso Música tema (/api/musicas).
 */
@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    private final MusicaService service;

    @Autowired
    public MusicaController(MusicaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Musica>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musica> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Musica> criar(@Valid @RequestBody Musica musica) {
        Musica salvo = service.criar(musica);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Musica> atualizar(@PathVariable Long id, @Valid @RequestBody Musica musica) {
        return ResponseEntity.ok(service.atualizar(id, musica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
