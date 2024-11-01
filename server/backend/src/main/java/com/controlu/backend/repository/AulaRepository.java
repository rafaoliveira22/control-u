package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AulaRepository extends JpaRepository<Aula, String> {
    Aula findTopByOrderByAulaIdDesc();

    List<Aula> findAllByOrderByAulaAberturaDesc();
    Optional<Aula> findBySalaId(String salaId);

    @Query("SELECT a FROM Aula a WHERE a.gradeId = :gradeId AND DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Optional<Aula> findAulaByGradeIdAndAulaAberturaTodayAndAulaFechamentoNull(String gradeId);

    @Query("SELECT a FROM Aula a WHERE a.aulaId = :aulaId AND DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Optional<Aula> findAulaByAulaIdAndAulaAberturaTodayAndAulaFechamentoNull(Integer aulaId);

    @Query("SELECT a FROM Aula a WHERE a.salaId = :salaId AND DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Optional<Aula> findAulaBySalaIdAndAulaAberturaTodayAndAulaFechamentoNull(String salaId);

    @Query("SELECT COUNT(a) FROM Aula a WHERE DATE(a.aulaAbertura) = CURRENT_DATE AND a.aulaFechamento IS NULL")
    Integer countAulasAcontecendo();

    @Query("SELECT a FROM Aula a WHERE " +
            "(:aulaId IS NULL OR a.aulaId = :aulaId) AND " +
            "(:dataInicial IS NULL OR DATE(a.aulaAbertura) >= :dataInicial) AND " +
            "(:dataFinal IS NULL OR a.aulaFechamento IS NULL OR DATE(a.aulaFechamento) <= :dataFinal) AND " +
            "(:gradeId IS NULL OR a.gradeId = :gradeId) AND " +
            "(:salaId IS NULL OR a.salaId = :salaId) " +
            "ORDER BY " +
            "CASE WHEN a.aulaFechamento IS NOT NULL THEN a.aulaFechamento ELSE a.aulaAbertura END DESC")
    List<Aula> buscarPorFiltros(
           @Param("aulaId") String aulaId,
           @Param("dataInicial") LocalDate dataInicial,
           @Param("dataFinal") LocalDate dataFinal,
           @Param("gradeId") String gradeId,
           @Param("salaId") String salaId
    );

    @Modifying
    @Query(value = "UPDATE aula a SET aula_fechamento = NOW() WHERE aula_id = :aulaId", nativeQuery = true)
    int atualizarHorarioAulaFechamentoParaDataHoraAtual(@Param("aulaId") String aulaId);
}
