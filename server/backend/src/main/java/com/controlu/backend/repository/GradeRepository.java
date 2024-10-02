package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    long countByCartaoId(String cartaoId);

    Optional<Grade> findByCartaoId(String cartaoId);
}
