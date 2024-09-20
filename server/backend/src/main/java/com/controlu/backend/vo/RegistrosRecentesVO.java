package com.controlu.backend.vo;

public class RegistrosRecentesVO {
    private String tipo;
    private String dataFormatada;
    private String horario;
    private String referencia;
    private String descricao;

    public RegistrosRecentesVO(){}

    public RegistrosRecentesVO(String tipo, String dataFormatada, String horario, String referencia, String descricao) {
        this.tipo = tipo;
        this.dataFormatada = dataFormatada;
        this.horario = horario;
        this.referencia = referencia;
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataFormatada() {
        return dataFormatada;
    }

    public void setDataFormatada(String dataFormatada) {
        this.dataFormatada = dataFormatada;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
