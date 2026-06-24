package com.copa2026.repository;

import com.copa2026.model.Campeao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeaoRepository extends JpaRepository<Campeao, Long> {
}
