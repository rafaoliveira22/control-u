package com.controlu.backend.vo;

public class FiltroRelatorioAcessoVO extends FiltroRelatorioVO{
    private Integer acessoId;
    private String dispositivoId;

    private String alunoId;

    public FiltroRelatorioAcessoVO(){}

    public FiltroRelatorioAcessoVO(Integer acessoId, String salaId, String alunoId) {
        this.acessoId = acessoId;
        this.dispositivoId = salaId;
        this.alunoId = alunoId;
    }

    public FiltroRelatorioAcessoVO(String dataInicial, String dataFinal, String tipo, Integer acessoId, String salaId, String alunoId) {
        super(dataInicial, dataFinal, tipo);
        this.acessoId = acessoId;
        this.dispositivoId = salaId;
        this.alunoId = alunoId;
    }

    public Integer getAcessoId() {
        return acessoId;
    }

    public void setAcessoId(Integer acessoId) {
        this.acessoId = acessoId;
    }

    public String getDispositivoId() {
        return dispositivoId;
    }

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    public void setDispositivoId(String dispositivoId) {
        this.dispositivoId = dispositivoId;
    }

    @Override
    public String toString() {
        return "FiltroRelatorioAcessoVO{" +
                "acessoId=" + acessoId +
                ", dispositivoId='" + dispositivoId + '\'' +
                ", alunoId='" + alunoId + '\'' +
                '}';
    }
}
