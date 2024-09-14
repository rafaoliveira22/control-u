package com.controlu.backend.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDateTime;

@Entity
@Table(name = "acesso")
public class Acesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acesso_id")
    private Integer acessoId;

    @Column(name = "acesso_entrada")
    private LocalDateTime acessoEntrada;

    @Column(name = "acesso_saida")
    private LocalDateTime acessoSaida;

    @Column(name = "dispositivo_id")
    private String dispositivoId ;

    @Column(name = "aluno_id")
    private String alunoId ;

    public Acesso(){}

    public Acesso(Integer acessoId, LocalDateTime acessoEntrada, LocalDateTime acessoSaida, String dispositivoId, String alunoId) {
        this.acessoId = acessoId;
        this.acessoEntrada = acessoEntrada;
        this.acessoSaida = acessoSaida;
        this.dispositivoId = dispositivoId;
        this.alunoId = alunoId;
    }

    public Integer getAcessoId() {
        return acessoId;
    }

    public void setAcessoId(Integer acessoId) {
        this.acessoId = acessoId;
    }

    public LocalDateTime getAcessoEntrada() {
        return acessoEntrada;
    }

    public void setAcessoEntrada(LocalDateTime acessoEntrada) {
        this.acessoEntrada = acessoEntrada;
    }

    public LocalDateTime getAcessoSaida() {
        return acessoSaida;
    }

    public void setAcessoSaida(LocalDateTime acessoSaida) {
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
}
