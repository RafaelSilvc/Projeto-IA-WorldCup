package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Mascote;
import com.copa2026.repository.MascoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascoteService {

    private final MascoteRepository repository;

    @Autowired
    public MascoteService(MascoteRepository repository) {
        this.repository = repository;
    }

    public List<Mascote> listarTodos() {
        return repository.findAll();
    }

    public Mascote buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascote não encontrado com id: " + id));
    }

    public Mascote criar(Mascote mascote) {
        return repository.save(mascote);
    }

    public Mascote atualizar(Long id, Mascote dadosAtualizados) {
        Mascote existente = buscarPorId(id);
        existente.setNome(dadosAtualizados.getNome());
        existente.setAnoCopa(dadosAtualizados.getAnoCopa());
        existente.setPaisSede(dadosAtualizados.getPaisSede());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Mascote existente = buscarPorId(id);
        repository.delete(existente);
    }
}
