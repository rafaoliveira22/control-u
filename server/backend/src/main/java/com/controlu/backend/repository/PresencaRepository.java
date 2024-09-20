package com.controlu.backend.repository;

import com.controlu.backend.entity.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresencaRepository extends JpaRepository<Presenca, Integer> {
    List<Presenca> findAllByOrderByPresencaEntradaDesc();
    List<Presenca> findByAulaIdAndAlunoId(Integer aulaId, String alunoId);

    @Query("SELECT p FROM Presenca p WHERE p.aulaId = :aulaId AND p.alunoId = :alunoId AND DATE(p.presencaEntrada) = CURRENT_DATE AND p.presencaSaida IS NULL")
    Optional<Presenca> findByAulaIdAndAlunoIdAndPresencaEntradaTodayAndPresencaSaidaNull(Integer aulaId, String alunoId);

    @Query("SELECT COUNT(p) FROM Presenca p WHERE DATE(p.presencaEntrada) = CURRENT_DATE AND p.presencaSaida IS NULL")
    Integer countAlunosEmAula();

}
