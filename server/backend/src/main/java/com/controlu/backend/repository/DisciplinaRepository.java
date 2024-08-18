package com.controlu.backend.repository;

import com.controlu.backend.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, String> {
    List<Disciplina> findAllByOrderByDisciplinaNomeAsc();
}
