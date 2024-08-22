package com.controlu.backend.repository;

import com.controlu.backend.entity.CartaoLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoLeituraRepository extends JpaRepository<CartaoLeitura, String> {
    CartaoLeitura findTopByOrderByCartaoIdDesc();
}
