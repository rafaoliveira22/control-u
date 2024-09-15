package com.controlu.backend.repository;

import com.controlu.backend.entity.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
    List<Aula> findAllByOrderByAulaAberturaDesc();

    @Query("SELECT a FROM Aula a WHERE a.gradeId = :gradeId AND DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NOT NULL")
    Optional<Aula> findAulaByGradeIdAndAulaAberturaTodayAndAulaFechamentoNotNull(Integer gradeId);
}