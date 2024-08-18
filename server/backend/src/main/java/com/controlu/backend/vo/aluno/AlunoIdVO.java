package com.controlu.backend.vo.aluno;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AlunoIdVO {
    @NotEmpty(message = "O nome do aluno não pode ser vazio.")
    @Size(min = 1, message = "O nome do aluno deve conter pelo menos 1 caractere.")
    private String alunoNome;

    @NotNull(message = "O ID do curso não pode ser nulo.")
    @Min(value = 1, message = "O ID do curso deve ser maior que zero.")
    private Integer cursoId;

    @NotNull(message = "O ano de ingresso do aluno não pode ser nulo.")
    @Min(value = 2019, message = "O ano de ingresso do aluno deve ser maior que 2019.")
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
