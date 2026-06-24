package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Campeao;
import com.copa2026.repository.CampeaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeaoService {

    private final CampeaoRepository repository;

    @Autowired
    public CampeaoService(CampeaoRepository repository) {
        this.repository = repository;
    }

    public List<Campeao> listarTodos() {
        return repository.findAll();
    }

    public Campeao buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Campeão não encontrado com id: " + id));
    }

    public Campeao criar(Campeao campeao) {
        return repository.save(campeao);
    }

    public Campeao atualizar(Long id, Campeao dadosAtualizados) {
        Campeao existente = buscarPorId(id);
        existente.setPais(dadosAtualizados.getPais());
        existente.setAno(dadosAtualizados.getAno());
        existente.setTecnico(dadosAtualizados.getTecnico());
        existente.setArtilheiro(dadosAtualizados.getArtilheiro());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Campeao existente = buscarPorId(id);
        repository.delete(existente);
    }
}
