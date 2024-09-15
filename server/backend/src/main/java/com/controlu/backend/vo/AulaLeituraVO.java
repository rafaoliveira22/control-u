package com.controlu.backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.time.OffsetDateTime;

public class AulaLeituraVO extends RepresentationModel<AlunoVO> {
    private String dispositivoId;
    private String cartaoId;

    public AulaLeituraVO(){}


    public AulaLeituraVO(String dispositivoId, String cartaoId) {
        this.dispositivoId = dispositivoId;
        this.cartaoId = cartaoId;
    }



    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(String cartaoId) {
        this.cartaoId = cartaoId;
    }

    @Override
    public String toString() {
        return "AulaVO{" +
                "dispositivoId='" + dispositivoId + '\'' +
                ", cartaoId='" + cartaoId + '\'' +
                '}';
    }
}
