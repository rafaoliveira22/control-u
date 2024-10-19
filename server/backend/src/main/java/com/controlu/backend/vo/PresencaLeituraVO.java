package com.controlu.backend.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PresencaLeituraVO {
    private String alunoId;

    private String dispositivoId;

    public PresencaLeituraVO(){}

    public PresencaLeituraVO(String alunoId, String dispositivoId) {
        this.alunoId = alunoId;
        this.dispositivoId = dispositivoId;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    @Override
    public String toString() {
        return "PresencaLeituraVO{" +
                "alunoId='" + alunoId + '\'' +
                ", dispositivoId='" + dispositivoId + '\'' +
                '}';
    }
}
