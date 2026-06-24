package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Bola;
import com.copa2026.repository.BolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BolaService {

    private final BolaRepository repository;

    @Autowired
    public BolaService(BolaRepository repository) {
        this.repository = repository;
    }

    public List<Bola> listarTodos() {
        return repository.findAll();
    }

    public Bola buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bola não encontrada com id: " + id));
    }

    public Bola criar(Bola bola) {
        return repository.save(bola);
    }

    public Bola atualizar(Long id, Bola dadosAtualizados) {
        Bola existente = buscarPorId(id);
        existente.setNomeOficial(dadosAtualizados.getNomeOficial());
        existente.setAnoCopa(dadosAtualizados.getAnoCopa());
        existente.setFabricante(dadosAtualizados.getFabricante());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Bola existente = buscarPorId(id);
        repository.delete(existente);
    }
}
