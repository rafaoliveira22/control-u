package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class CartaoLeituraVO extends RepresentationModel<CartaoLeituraVO> {
    private String cartaoId;
    private Integer status;

    public CartaoLeituraVO(){}

    public CartaoLeituraVO(String cartaoId, Integer status) {
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
        return "CartaoLeituraVO{" +
                "cartaoId='" + cartaoId + '\'' +
                ", status=" + status +
                '}';
    }
}
