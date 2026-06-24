package com.copa2026.controller;

import com.copa2026.model.Artilheiro;
import com.copa2026.service.ArtilheiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso "artilheiros por edição da copa".
 * Mapeado em /api/jogadores/artilheiros.
 */
@RestController
@RequestMapping("/api/jogadores/artilheiros")
public class ArtilheiroController {

    private final ArtilheiroService service;

    @Autowired
    public ArtilheiroController(ArtilheiroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Artilheiro>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artilheiro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Artilheiro> criar(@Valid @RequestBody Artilheiro artilheiro) {
        Artilheiro salvo = service.criar(artilheiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artilheiro> atualizar(@PathVariable Long id, @Valid @RequestBody Artilheiro artilheiro) {
        return ResponseEntity.ok(service.atualizar(id, artilheiro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
