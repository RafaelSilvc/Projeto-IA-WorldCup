package com.copa2026.controller;

import com.copa2026.model.Campeao;
import com.copa2026.service.CampeaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso Campeão (/api/campeoes).
 */
@RestController
@RequestMapping("/api/campeoes")
public class CampeaoController {

    private final CampeaoService service;

    @Autowired
    public CampeaoController(CampeaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Campeao>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campeao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Campeao> criar(@Valid @RequestBody Campeao campeao) {
        Campeao salvo = service.criar(campeao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campeao> atualizar(@PathVariable Long id, @Valid @RequestBody Campeao campeao) {
        return ResponseEntity.ok(service.atualizar(id, campeao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
