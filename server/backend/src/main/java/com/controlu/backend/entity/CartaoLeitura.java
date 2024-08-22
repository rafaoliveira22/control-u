package com.controlu.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartao_leitura")
public class CartaoLeitura {
    @Id
    @Column(name = "cartao_id")
    private String cartaoId;

    @Column(name = "status")
    private Integer status;

    public CartaoLeitura(){}

    public CartaoLeitura(String cartaoId, Integer status) {
        this.cartaoId = cartaoId;
        this.status = status;
    }

    public String getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(String cartaoId) {
        this.cartaoId = cartaoId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartaoLeitura{" +
                "cartaoId='" + cartaoId + '\'' +
                ", status=" + status +
                '}';
    }
}
