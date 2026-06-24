package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Pais;
import com.copa2026.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Camada de regras de negócio para o recurso País.
 * Faz a ponte entre o Controller e o Repository.
 */
@Service
public class PaisService {

    private final PaisRepository repository;

    @Autowired
    public PaisService(PaisRepository repository) {
        this.repository = repository;
    }

    public List<Pais> listarTodos() {
        return repository.findAll();
    }

    public Pais buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("País não encontrado com id: " + id));
    }

    public Pais criar(Pais pais) {
        return repository.save(pais);
    }

    public Pais atualizar(Long id, Pais dadosAtualizados) {
        Pais existente = buscarPorId(id); // lança 404 se não existir
        existente.setNome(dadosAtualizados.getNome());
        existente.setSigla(dadosAtualizados.getSigla());
        existente.setConfederacao(dadosAtualizados.getConfederacao());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Pais existente = buscarPorId(id); // lança 404 se não existir
        repository.delete(existente);
    }
}
