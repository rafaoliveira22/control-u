package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Integer> {

    @Query("SELECT a FROM Acesso a WHERE a.alunoId = :alunoId AND DATE(a.acessoEntrada) = CURRENT_DATE AND a.acessoSaida IS NULL")
    Optional<Acesso> findAcessoByAlunoIdAndAcessoEntradaTodayAndAcessoSaidaNull(String alunoId);

    List<Acesso> findAllByOrderByAcessoEntradaDesc();

    @Query("SELECT COUNT(a) FROM Acesso a WHERE DATE(a.acessoEntrada) = CURRENT_DATE AND a.acessoSaida IS NULL")
    Integer countAcessosDataAtualAndAcessoSaidaNull();
}
