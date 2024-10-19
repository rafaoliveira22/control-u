package com.controlu.backend.vo;

public class AcessoLeituraVO {
    private String dispositivoId ;

    private String alunoId;

    private String faceEntrada;

    public AcessoLeituraVO(){}

    public AcessoLeituraVO(String dispositivoId, String alunoId) {
        this.dispositivoId = dispositivoId;
        this.alunoId = alunoId;
    }

    public AcessoLeituraVO(String dispositivoId, String alunoId, String faceEntrada) {
        this.dispositivoId = dispositivoId;
        this.alunoId = alunoId;
        this.faceEntrada = faceEntrada;
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

    public String getFaceEntrada() {
        return faceEntrada;
    }

    public void setFaceEntrada(String faceEntrada) {
        this.faceEntrada = faceEntrada;
    }

    @Override
    public String toString() {
        return "AcessoLeituraVO{" +
                "dispositivoId='" + dispositivoId + '\'' +
                ", alunoId='" + alunoId + '\'' +
                ", faceEntrada='" + faceEntrada + '\'' +
                '}';
    }
}
