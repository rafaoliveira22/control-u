package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    boolean existsByCursoNome(String cursoNome);
}
