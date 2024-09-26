package com.controlu.backend.vo;

public class FiltroRelatorioAcessoVO extends FiltroRelatorioVO{
    private Integer acessoId;
    private String salaId;

    public FiltroRelatorioAcessoVO(){}

    public FiltroRelatorioAcessoVO(Integer acessoId, String salaId) {
        this.acessoId = acessoId;
        this.salaId = salaId;
    }

    public FiltroRelatorioAcessoVO(String dataInicial, String dataFinal, String tipo, String alunoId, Integer acessoId, String salaId) {
        super(dataInicial, dataFinal, tipo, alunoId);
        this.acessoId = acessoId;
        this.salaId = salaId;
    }

    public Integer getAcessoId() {
        return acessoId;
    }

    public void setAcessoId(Integer acessoId) {
        this.acessoId = acessoId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }
}
