package com.copa2026.controller;

import com.copa2026.model.Pais;
import com.copa2026.service.PaisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso País.
 * GET    /api/paises          -> lista todos
 * GET    /api/paises/{id}     -> busca por id
 * POST   /api/paises          -> cria
 * PUT    /api/paises/{id}     -> edita
 * DELETE /api/paises/{id}     -> exclui
 */
@RestController
@RequestMapping("/api/paises")
public class PaisController {

    private final PaisService service;

    @Autowired
    public PaisController(PaisService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Pais>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Pais> criar(@Valid @RequestBody Pais pais) {
        Pais salvo = service.criar(pais);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> atualizar(@PathVariable Long id, @Valid @RequestBody Pais pais) {
        return ResponseEntity.ok(service.atualizar(id, pais));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
