package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Artilheiro;
import com.copa2026.repository.ArtilheiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtilheiroService {

    private final ArtilheiroRepository repository;

    @Autowired
    public ArtilheiroService(ArtilheiroRepository repository) {
        this.repository = repository;
    }

    public List<Artilheiro> listarTodos() {
        return repository.findAll();
    }

    public Artilheiro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Artilheiro não encontrado com id: " + id));
    }

    public Artilheiro criar(Artilheiro artilheiro) {
        return repository.save(artilheiro);
    }

    public Artilheiro atualizar(Long id, Artilheiro dadosAtualizados) {
        Artilheiro existente = buscarPorId(id);
        existente.setJogador(dadosAtualizados.getJogador());
        existente.setAnoCopa(dadosAtualizados.getAnoCopa());
        existente.setTotalGols(dadosAtualizados.getTotalGols());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Artilheiro existente = buscarPorId(id);
        repository.delete(existente);
    }
}
