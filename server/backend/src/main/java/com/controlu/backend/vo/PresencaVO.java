package com.controlu.backend.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.RepresentationModel;

import java.time.OffsetDateTime;

public class PresencaVO extends RepresentationModel<PresencaVO> {
    private Integer presencaId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime presencaEntrada;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private OffsetDateTime presencaSaida;
    private String alunoId;
    private Integer aulaId;

    @JsonIgnore
    private String dispositivoId;

    public PresencaVO(){}

    public PresencaVO(Integer presencaId, OffsetDateTime presencaEntrada, OffsetDateTime presencaSaida, String alunoId, Integer aulaId) {
        this.presencaId = presencaId;
        this.presencaEntrada = presencaEntrada;
        this.presencaSaida = presencaSaida;
        this.alunoId = alunoId;
        this.aulaId = aulaId;
    }

    public PresencaVO(Integer presencaId, OffsetDateTime presencaEntrada, OffsetDateTime presencaSaida, String alunoId, Integer aulaId, String dispositivoId) {
        this.presencaId = presencaId;
        this.presencaEntrada = presencaEntrada;
        this.presencaSaida = presencaSaida;
        this.alunoId = alunoId;
        this.dispositivoId = dispositivoId;
    }

    public Integer getPresencaId() {
        return presencaId;
    }

    public void setPresencaId(Integer presencaId) {
        this.presencaId = presencaId;
    }

    public OffsetDateTime getPresencaEntrada() {
        return presencaEntrada;
    }

    public void setPresencaEntrada(OffsetDateTime presencaEntrada) {
        this.presencaEntrada = presencaEntrada;
    }

    public OffsetDateTime getPresencaSaida() {
        return presencaSaida;
    }

    public void setPresencaSaida(OffsetDateTime presencaSaida) {
        this.presencaSaida = presencaSaida;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
        this.aulaId = aulaId;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }
}
