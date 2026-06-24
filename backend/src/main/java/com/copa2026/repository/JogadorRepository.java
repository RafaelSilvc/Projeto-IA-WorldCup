package com.copa2026.repository;

import com.copa2026.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    // Query derivada pelo Spring Data JPA: busca jogadores cujo nome OU país
    // contenham o texto informado (ignorando maiúsculas/minúsculas).
    List<Jogador> findByNomeContainingIgnoreCaseOrPaisContainingIgnoreCase(String nome, String pais);
}
