package com.copa2026.controller;

import com.copa2026.model.Estadio;
import com.copa2026.service.EstadioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso Estádio (/api/estadios).
 */
@RestController
@RequestMapping("/api/estadios")
public class EstadioController {

    private final EstadioService service;

    @Autowired
    public EstadioController(EstadioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Estadio>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estadio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Estadio> criar(@Valid @RequestBody Estadio estadio) {
        Estadio salvo = service.criar(estadio);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estadio> atualizar(@PathVariable Long id, @Valid @RequestBody Estadio estadio) {
        return ResponseEntity.ok(service.atualizar(id, estadio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
