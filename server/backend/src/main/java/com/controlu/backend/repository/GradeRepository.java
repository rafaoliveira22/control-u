package com.controlu.backend.repository;

import com.controlu.backend.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    long countByCartaoId(String cartaoId);
}
