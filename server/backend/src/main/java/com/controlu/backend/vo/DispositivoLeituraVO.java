package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class DispositivoLeituraVO extends RepresentationModel<DispositivoLeituraVO> {
    private String dispositivoId;

    private Integer dispositivoTipo;

    private Integer dispositivoStatus;

    public DispositivoLeituraVO(){}

    public DispositivoLeituraVO(String dispositivoId, Integer dispositivoTipo, Integer dispositivoStatus) {
        this.dispositivoId = dispositivoId;
        this.dispositivoTipo = dispositivoTipo;
        this.dispositivoStatus = dispositivoStatus;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    public Integer getDispositivoTipo() {
        return dispositivoTipo;
    }

    public void setDispositivoTipo(Integer dispositivoTipo) {
        this.dispositivoTipo = dispositivoTipo;
    }

    public Integer getDispositivoStatus() {
        return dispositivoStatus;
    }

    public void setDispositivoStatus(Integer dispositivoStatus) {
        this.dispositivoStatus = dispositivoStatus;
    }

    @Override
    public String toString() {
        return "DispositivoLeituraVO{" +
                "dispositivoId='" + dispositivoId + '\'' +
                ", dispositivoTipo=" + dispositivoTipo +
                ", dispositivoStatus=" + dispositivoStatus +
                '}';
    }
}
