package com.controlu.backend.vo;

public class AlunoIdVO {
    private String alunoNome;
    private Integer cursoId;
    private Integer alunoAnoIngressao;

    public AlunoIdVO() {}

    public AlunoIdVO(String alunoNome, Integer cursoId, Integer alunoAnoIngressao) {
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
        this.alunoAnoIngressao = alunoAnoIngressao;
    }

    // Getters and Setters

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public Integer getAlunoAnoIngressao() {
        return alunoAnoIngressao;
    }

    public void setAlunoAnoIngressao(Integer alunoAnoIngressao) {
        this.alunoAnoIngressao = alunoAnoIngressao;
    }

    @Override
    public String toString() {
        return "AlunoIdVO{" +
                "alunoNome='" + alunoNome + '\'' +
                ", cursoId=" + cursoId +
                ", alunoAnoIngressao=" + alunoAnoIngressao +
                '}';
    }
}
