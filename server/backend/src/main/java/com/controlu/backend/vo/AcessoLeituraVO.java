package com.controlu.backend.vo;

public class AcessoLeituraVO {
    private String dispositivoId ;

    private String alunoId;

    public AcessoLeituraVO(){}

    public AcessoLeituraVO(String dispositivoId, String alunoId) {
        this.dispositivoId = dispositivoId;
        this.alunoId = alunoId;
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

    @Override
    public String toString() {
        return "AcessoLeituraVO{" +
                "dispositivoId='" + dispositivoId + '\'' +
                ", alunoId='" + alunoId + '\'' +
                '}';
    }
}
