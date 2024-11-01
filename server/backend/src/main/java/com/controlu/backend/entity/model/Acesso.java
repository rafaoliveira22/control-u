package com.controlu.backend.entity.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "acesso")
public class Acesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acesso_id")
    private Integer acessoId;

    @Column(name = "acesso_entrada", insertable = false, updatable = false)
    private OffsetDateTime acessoEntrada;

    @Column(name = "acesso_saida")
    private OffsetDateTime acessoSaida;

    @Column(name = "dispositivo_id")
    private String dispositivoId ;

    @Column(name = "aluno_id")
    private String alunoId ;

    @Lob
    @Column(name = "acesso_face_momento_entrada")
    private byte[] acessoFaceMomentoEntrada;

    public Acesso(){}

    public Acesso(Integer acessoId, OffsetDateTime acessoEntrada, OffsetDateTime acessoSaida, String dispositivoId, String alunoId) {
        this.acessoId = acessoId;
        this.acessoEntrada = acessoEntrada;
        this.acessoSaida = acessoSaida;
        this.dispositivoId = dispositivoId;
        this.alunoId = alunoId;
    }

    public Acesso(Integer acessoId, OffsetDateTime acessoEntrada, OffsetDateTime acessoSaida, String dispositivoId, String alunoId, byte[] acessoFaceMomentoEntrada) {
        this.acessoId = acessoId;
        this.acessoEntrada = acessoEntrada;
        this.acessoSaida = acessoSaida;
        this.dispositivoId = dispositivoId;
        this.alunoId = alunoId;
        this.acessoFaceMomentoEntrada = acessoFaceMomentoEntrada;
    }

    public Integer getAcessoId() {
        return acessoId;
    }

    public void setAcessoId(Integer acessoId) {
        this.acessoId = acessoId;
    }

    public OffsetDateTime getAcessoEntrada() {
        return acessoEntrada;
    }

    public void setAcessoEntrada(OffsetDateTime acessoEntrada) {
        this.acessoEntrada = acessoEntrada;
    }

    public OffsetDateTime getAcessoSaida() {
        return acessoSaida;
    }

    public void setAcessoSaida(OffsetDateTime acessoSaida) {
        this.acessoSaida = acessoSaida;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public byte[] getAcessoFaceMomentoEntrada() {
        return acessoFaceMomentoEntrada;
    }

    public void setAcessoFaceMomentoEntrada(byte[] acessoFaceMomentoEntrada) {
        this.acessoFaceMomentoEntrada = acessoFaceMomentoEntrada;
    }

    @Override
    public String toString() {
        return "Acesso{" +
                "acessoId=" + acessoId +
                ", acessoEntrada=" + acessoEntrada +
                ", acessoSaida=" + acessoSaida +
                ", dispositivoId='" + dispositivoId + '\'' +
                ", alunoId='" + alunoId + '\'' +
                '}';
    }
}
