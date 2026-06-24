package com.copa2026.repository;

import com.copa2026.model.DesempenhoJogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesempenhoJogadorRepository extends JpaRepository<DesempenhoJogador, Long> {
}
