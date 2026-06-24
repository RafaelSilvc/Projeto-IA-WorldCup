package com.copa2026.repository;

import com.copa2026.model.Final;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalRepository extends JpaRepository<Final, Long> {
}
