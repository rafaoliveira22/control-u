package com.controlu.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dispositivo_leitura")
public class DispositivoLeitura {
    @Id
    @Column(name = "dispositivo_id")
    private String dispositivoId;

    @Column(name = "dispositivo_status")
    private Integer dispositivoStatus;

    public DispositivoLeitura(){}

    public DispositivoLeitura(String dispositivoId, Integer dispositivoStatus) {
        this.dispositivoId = dispositivoId;
        this.dispositivoStatus = dispositivoStatus;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public Integer getDispositivoStatus() {
        return dispositivoStatus;
    }

    public void setDispositivoStatus(Integer dispositivoStatus) {
        this.dispositivoStatus = dispositivoStatus;
    }

    @Override
    public String toString() {
        return "DispositivoLeitura{" +
                "dispositivoId='" + dispositivoId + '\'' +
                ", dispositivoStatus=" + dispositivoStatus +
                '}';
    }
}
