package com.copa2026.service;

import com.copa2026.exception.ResourceNotFoundException;
import com.copa2026.model.Jogador;
import com.copa2026.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    private final JogadorRepository repository;

    @Autowired
    public JogadorService(JogadorRepository repository) {
        this.repository = repository;
    }

    public List<Jogador> listarTodos() {
        return repository.findAll();
    }

    // Busca por nome ou país, usada pelo campo de busca do frontend (?busca=texto)
    public List<Jogador> buscar(String termo) {
        return repository.findByNomeContainingIgnoreCaseOrPaisContainingIgnoreCase(termo, termo);
    }

    public Jogador buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador não encontrado com id: " + id));
    }

    public Jogador criar(Jogador jogador) {
        return repository.save(jogador);
    }

    public Jogador atualizar(Long id, Jogador dadosAtualizados) {
        Jogador existente = buscarPorId(id);
        existente.setNome(dadosAtualizados.getNome());
        existente.setPais(dadosAtualizados.getPais());
        existente.setPosicao(dadosAtualizados.getPosicao());
        existente.setNumeroCamisa(dadosAtualizados.getNumeroCamisa());
        existente.setTotalGols(dadosAtualizados.getTotalGols());
        existente.setCopasDisputadas(dadosAtualizados.getCopasDisputadas());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        Jogador existente = buscarPorId(id);
        repository.delete(existente);
    }
}
