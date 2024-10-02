package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Presenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    /**
     * Filtros: dataInicial, dataFinal,tipo, alunoId,presencaId,aulaId,gradeId (o filtro de grade ainda nao foi implementado)
     * Basicamente, a lógica dessa query é buscar a presença com base nas condições, sendo que, se algum dos filtros não for passado, ou seja,
     * for null, ele seja ignorado na consulta.
     */
    @Query("SELECT p FROM Presenca p WHERE " +
            "(:alunoId IS NULL OR p.alunoId = :alunoId) AND " +
            "(:aulaId IS NULL OR p.aulaId = :aulaId) AND " +
            "(:dataInicial IS NULL OR DATE(p.presencaEntrada) >= :dataInicial) AND " +
            "(:dataFinal IS NULL OR p.presencaSaida IS NULL OR DATE(p.presencaSaida) <= :dataFinal)")
    List<Presenca> buscarPorFiltros(
            @Param("alunoId") String alunoId,
            @Param("aulaId") Integer aulaId,
            @Param("dataInicial") LocalDate dataInicial,
            @Param("dataFinal") LocalDate dataFinal
    );

}
