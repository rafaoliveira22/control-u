package com.controlu.backend.repository;

import com.controlu.backend.entity.model.CartaoLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoLeituraRepository extends JpaRepository<CartaoLeitura, String> {
    CartaoLeitura findTopByOrderByCartaoIdDesc();
    @Query("SELECT c FROM CartaoLeitura c LEFT JOIN Grade g ON c.cartaoId = g.cartaoId WHERE g.cartaoId IS NULL")
    List<CartaoLeitura> findCartoesNotInGrade();
}
