package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Estadio;
import com.copa2026.repository.EstadioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadioService {

    private final EstadioRepository repository;

    @Autowired
    public EstadioService(EstadioRepository repository) {
        this.repository = repository;
    }

    public List<Estadio> listarTodos() {
        return repository.findAll();
    }

    public Estadio buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estádio não encontrado com id: " + id));
    }

    public Estadio criar(Estadio estadio) {
        return repository.save(estadio);
    }

    public Estadio atualizar(Long id, Estadio dadosAtualizados) {
        Estadio existente = buscarPorId(id);
        existente.setNome(dadosAtualizados.getNome());
        existente.setCidade(dadosAtualizados.getCidade());
        existente.setPaisSede(dadosAtualizados.getPaisSede());
        existente.setCapacidade(dadosAtualizados.getCapacidade());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Estadio existente = buscarPorId(id);
        repository.delete(existente);
    }
}
