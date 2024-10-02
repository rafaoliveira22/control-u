package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, String> {
    List<Professor> findAllByOrderByProfessorNomeAsc();
}
