package com.controlu.backend.repository;

import com.controlu.backend.entity.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AcessoRepository extends JpaRepository<Acesso, Integer> {

    @Query("SELECT a FROM Acesso a WHERE a.alunoId = :alunoId AND DATE(a.acessoEntrada) = CURRENT_DATE")
    Optional<Acesso> findAcessoByAlunoIdAndAcessoEntradaToday(String alunoId);

    List<Acesso> findAllByOrderByAcessoEntradaDesc();
}
