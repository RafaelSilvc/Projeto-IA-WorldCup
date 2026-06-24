package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Final;
import com.copa2026.repository.FinalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalService {

    private final FinalRepository repository;

    @Autowired
    public FinalService(FinalRepository repository) {
        this.repository = repository;
    }

    public List<Final> listarTodos() {
        return repository.findAll();
    }

    public Final buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Final não encontrada com id: " + id));
    }

    public Final criar(Final finalPartida) {
        return repository.save(finalPartida);
    }

    public Final atualizar(Long id, Final dadosAtualizados) {
        Final existente = buscarPorId(id);
        existente.setAnoCopa(dadosAtualizados.getAnoCopa());
        existente.setPaisCampeao(dadosAtualizados.getPaisCampeao());
        existente.setPaisVice(dadosAtualizados.getPaisVice());
        existente.setGolsCampeao(dadosAtualizados.getGolsCampeao());
        existente.setGolsVice(dadosAtualizados.getGolsVice());
        existente.setEstadio(dadosAtualizados.getEstadio());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Final existente = buscarPorId(id);
        repository.delete(existente);
    }
}
