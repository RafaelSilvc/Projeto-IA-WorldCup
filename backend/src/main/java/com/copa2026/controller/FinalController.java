package com.copa2026.controller;

import com.copa2026.model.Final;
import com.copa2026.service.FinalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Endpoints RESTful do recurso histórico de Finais (/api/finais).
 */
@RestController
@RequestMapping("/api/finais")
public class FinalController {

    private final FinalService service;

    @Autowired
    public FinalController(FinalService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Final>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Final> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Final> criar(@Valid @RequestBody Final finalPartida) {
        Final salvo = service.criar(finalPartida);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Final> atualizar(@PathVariable Long id, @Valid @RequestBody Final finalPartida) {
        return ResponseEntity.ok(service.atualizar(id, finalPartida));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
