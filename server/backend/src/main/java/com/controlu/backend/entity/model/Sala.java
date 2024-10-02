package com.controlu.backend.entity.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name = "sala")
public class Sala {

    @Id
    @Column(name = "sala_id")
    private String salaId;

    @Column(name = "sala_nome")
    private String salaNome;

    @Column(name = "dispositivo_id", length = 10)
    private String dispositivoId;

    public Sala(){}

    public Sala(String salaId, String salaNome, String dispositivoId) {
        this.salaId = salaId;
        this.salaNome = salaNome;
        this.dispositivoId = dispositivoId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getSalaNome() {
        return salaNome;
    }

    public void setSalaNome(String salaNome) {
        this.salaNome = salaNome;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "salaId='" + salaId + '\'' +
                ", salaNome='" + salaNome + '\'' +
                ", dispositivoId='" + dispositivoId + '\'' +
                '}';
    }
}
