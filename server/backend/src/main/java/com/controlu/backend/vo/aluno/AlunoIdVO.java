package com.controlu.backend.vo.aluno;

import jakarta.validation.constraints.NotNull;

public class AlunoIdVO {

    @NotNull(message = "O Nome do aluno é obrigatório")
    private String alunoNome;

    @NotNull(message = "O ID do Curso é obrigatório")
    private Integer cursoId;

    @NotNull(message = "O Ano de Ingressão do aluno é obrigatório")
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
