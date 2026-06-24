package com.copa2026.repository;

import com.copa2026.model.Artilheiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtilheiroRepository extends JpaRepository<Artilheiro, Long> {
}
