package com.copa2026.controller;

import com.copa2026.model.Bola;
import com.copa2026.service.BolaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso Bola oficial (/api/bolas).
 */
@RestController
@RequestMapping("/api/bolas")
public class BolaController {

    private final BolaService service;

    @Autowired
    public BolaController(BolaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Bola>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bola> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Bola> criar(@Valid @RequestBody Bola bola) {
        Bola salvo = service.criar(bola);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bola> atualizar(@PathVariable Long id, @Valid @RequestBody Bola bola) {
        return ResponseEntity.ok(service.atualizar(id, bola));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
