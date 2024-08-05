package com.controlu.backend.repository;

import com.controlu.backend.entity.Aluno;
import com.controlu.backend.entity.embeddable.AlunoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, AlunoId> {
    Optional<Aluno> findByAlunoRa(String alunoRa);
}
