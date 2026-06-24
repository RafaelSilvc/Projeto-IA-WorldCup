package com.copa2026.controller;

import com.copa2026.model.Mascote;
import com.copa2026.service.MascoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso Mascote oficial (/api/mascotes).
 */
@RestController
@RequestMapping("/api/mascotes")
public class MascoteController {

    private final MascoteService service;

    @Autowired
    public MascoteController(MascoteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Mascote>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascote> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Mascote> criar(@Valid @RequestBody Mascote mascote) {
        Mascote salvo = service.criar(mascote);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascote> atualizar(@PathVariable Long id, @Valid @RequestBody Mascote mascote) {
        return ResponseEntity.ok(service.atualizar(id, mascote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
