package com.controlu.backend.repository;

import com.controlu.backend.entity.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Integer> {
    List<Presenca> findAllByOrderByPresencaEntradaDesc();
    Optional<Presenca> findByAulaIdAndAlunoId(Integer aulaId, String alunoId);
}
