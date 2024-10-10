package com.controlu.backend.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

public class AlunoVO extends RepresentationModel<AlunoVO> {


    @NotEmpty(message = "O RA do aluno não pode ser vazio.")
    @Size(min = 13, max = 13, message = "O RA do aluno deve conter 13 caracteres.")
    private String alunoRa;

    private String alunoNome;

    private Integer cursoId;

    private byte[] alunoFace;

    public AlunoVO(){}

    public AlunoVO(String alunoRa, String alunoNome, Integer cursoId){
        this.alunoRa = alunoRa;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
    }

    public AlunoVO(String alunoRa, String alunoNome, Integer cursoId, byte[] alunoFace) {
        this.alunoRa = alunoRa;
        this.alunoNome = alunoNome;
        this.cursoId = cursoId;
        this.alunoFace = alunoFace;
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

    public byte[] getAlunoFace() {
        return alunoFace;
    }

    public void setAlunoFace(byte[] alunoFace) {
        this.alunoFace = alunoFace;
    }
}
