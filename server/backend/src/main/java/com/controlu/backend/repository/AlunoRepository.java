package com.controlu.backend.repository;

import com.controlu.backend.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, String> {
    List<Aluno> findAllByOrderByAlunoNomeAsc();
}
