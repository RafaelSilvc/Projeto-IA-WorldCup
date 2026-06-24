package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Musica;
import com.copa2026.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicaService {

    private final MusicaRepository repository;

    @Autowired
    public MusicaService(MusicaRepository repository) {
        this.repository = repository;
    }

    public List<Musica> listarTodos() {
        return repository.findAll();
    }

    public Musica buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Música não encontrada com id: " + id));
    }

    public Musica criar(Musica musica) {
        return repository.save(musica);
    }

    public Musica atualizar(Long id, Musica dadosAtualizados) {
        Musica existente = buscarPorId(id);
        existente.setTitulo(dadosAtualizados.getTitulo());
        existente.setArtista(dadosAtualizados.getArtista());
        existente.setAnoCopa(dadosAtualizados.getAnoCopa());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Musica existente = buscarPorId(id);
        repository.delete(existente);
    }
}
