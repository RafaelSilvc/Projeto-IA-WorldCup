package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Partida;
import com.copa2026.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartidaService {

    private final PartidaRepository repository;

    @Autowired
    public PartidaService(PartidaRepository repository) {
        this.repository = repository;
    }

    public List<Partida> listarTodos() {
        return repository.findAll();
    }

    public Partida buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partida não encontrada com id: " + id));
    }

    public Partida criar(Partida partida) {
        return repository.save(partida);
    }

    public Partida atualizar(Long id, Partida dadosAtualizados) {
        Partida existente = buscarPorId(id);
        existente.setPaisMandante(dadosAtualizados.getPaisMandante());
        existente.setPaisVisitante(dadosAtualizados.getPaisVisitante());
        existente.setGolsMandante(dadosAtualizados.getGolsMandante());
        existente.setGolsVisitante(dadosAtualizados.getGolsVisitante());
        existente.setFase(dadosAtualizados.getFase());
        existente.setEstadio(dadosAtualizados.getEstadio());
        existente.setDataDaPartida(dadosAtualizados.getDataDaPartida());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Partida existente = buscarPorId(id);
        repository.delete(existente);
    }
}
