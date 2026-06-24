package com.copa2026.repository;

import com.copa2026.model.Bola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BolaRepository extends JpaRepository<Bola, Long> {
}
