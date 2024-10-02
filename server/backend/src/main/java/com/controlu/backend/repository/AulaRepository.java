package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer> {
    List<Aula> findAllByOrderByAulaAberturaDesc();
    Optional<Aula> findBySalaId(String salaId);

    @Query("SELECT a FROM Aula a WHERE a.gradeId = :gradeId AND DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Optional<Aula> findAulaByGradeIdAndAulaAberturaTodayAndAulaFechamentoNull(Integer gradeId);

    @Query("SELECT a FROM Aula a WHERE a.aulaId = :aulaId AND DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Optional<Aula> findAulaByAulaIdAndAulaAberturaTodayAndAulaFechamentoNull(Integer aulaId);

    @Query("SELECT a FROM Aula a WHERE a.salaId = :salaId AND DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Optional<Aula> findAulaBySalaIdAndAulaAberturaTodayAndAulaFechamentoNull(String salaId);

    @Query("SELECT COUNT(a) FROM Aula a WHERE DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Integer countAulasAcontecendo();
}
