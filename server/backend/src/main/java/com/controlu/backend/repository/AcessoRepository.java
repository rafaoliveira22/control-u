package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer> {

    @Query("SELECT a FROM Acesso a WHERE a.alunoId = :alunoId AND DATE(a.acessoEntrada) = CURRENT_DATE AND a.acessoSaida IS NULL")
    Optional<Acesso> findAcessoByAlunoIdAndAcessoEntradaTodayAndAcessoSaidaNull(String alunoId);

    List<Acesso> findAllByOrderByAcessoEntradaDesc();

    @Query("SELECT COUNT(a) FROM Acesso a WHERE DATE(a.acessoEntrada) = CURRENT_DATE AND a.acessoSaida IS NULL")
    Integer countAcessosDataAtualAndAcessoSaidaNull();

    @Query("SELECT a FROM Acesso a WHERE " +
            "(:acessoId IS NULL OR a.acessoId = :acessoId) AND " +
            "(:dataInicial IS NULL OR DATE(a.acessoEntrada) >= :dataInicial) AND " +
            "(:dataFinal IS NULL OR a.acessoSaida IS NULL OR DATE(a.acessoSaida) <= :dataFinal) AND " +
            "(:dispositivoId IS NULL OR a.dispositivoId = :dispositivoId) AND " +
            "(:alunoId IS NULL OR a.alunoId = :alunoId)")
            List<Acesso> buscarPorFiltros(
            @Param("acessoId") Integer acessoId,
            @Param("dataInicial") LocalDate dataInicial,
            @Param("dataFinal") LocalDate dataFinal,
            @Param("dispositivoId") String dispositivoId,
            @Param("alunoId") String alunoId);
}
