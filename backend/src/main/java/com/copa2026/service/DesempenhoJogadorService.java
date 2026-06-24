package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.DesempenhoJogador;
import com.copa2026.repository.DesempenhoJogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesempenhoJogadorService {

    private final DesempenhoJogadorRepository repository;

    @Autowired
    public DesempenhoJogadorService(DesempenhoJogadorRepository repository) {
        this.repository = repository;
    }

    public List<DesempenhoJogador> listarTodos() {
        return repository.findAll();
    }

    public DesempenhoJogador buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Desempenho não encontrado com id: " + id));
    }

    public DesempenhoJogador criar(DesempenhoJogador desempenho) {
        return repository.save(desempenho);
    }

    public DesempenhoJogador atualizar(Long id, DesempenhoJogador dadosAtualizados) {
        DesempenhoJogador existente = buscarPorId(id);
        existente.setJogador(dadosAtualizados.getJogador());
        existente.setCopa(dadosAtualizados.getCopa());
        existente.setGols(dadosAtualizados.getGols());
        existente.setAssistencias(dadosAtualizados.getAssistencias());
        existente.setPartidasJogadas(dadosAtualizados.getPartidasJogadas());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        DesempenhoJogador existente = buscarPorId(id);
        repository.delete(existente);
    }
}
