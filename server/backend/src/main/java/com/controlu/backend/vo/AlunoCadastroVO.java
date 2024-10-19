package com.controlu.backend.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

public class AlunoCadastroVO extends RepresentationModel<AlunoCadastroVO> {


    @NotEmpty(message = "O RA do aluno não pode ser vazio.")
    @Size(min = 13, max = 13, message = "O RA do aluno deve conter 13 caracteres.")
    private String alunoRa;

    private String alunoNome;

    private Integer cursoId;

    private String face;

    public AlunoCadastroVO(){}

    public AlunoCadastroVO(String alunoRa, String alunoNome, Integer cursoId){
        this.alunoRa = alunoRa;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
    }

    public AlunoCadastroVO(String alunoRa, String alunoNome, Integer cursoId, String alunoFace) {
        this.alunoRa = alunoRa;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
        this.face = alunoFace;
    }

    public String getAlunoRa() {
        return alunoRa;
    }

    public void setAlunoRa(String alunoRa) {
        this.alunoRa = alunoRa;
    }

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

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "AlunoCadastroVO{" +
                "alunoRa='" + alunoRa + '\'' +
                ", alunoNome='" + alunoNome + '\'' +
                ", cursoId=" + cursoId +
                ", face='" + face + '\'' +
                '}';
    }
}
