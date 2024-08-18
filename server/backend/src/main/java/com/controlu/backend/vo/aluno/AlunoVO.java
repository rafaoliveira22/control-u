package com.controlu.backend.vo.aluno;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.hateoas.RepresentationModel;

public class AlunoVO extends RepresentationModel<AlunoVO> {


    @NotNull(message = "O ID do aluno não pode ser nulo.")
    private AlunoIdVO id;

    @NotEmpty(message = "O RA do aluno não pode ser vazio.")
    @Size(min = 13, max = 13, message = "O RA do aluno deve conter 13 caracteres.")
    private String alunoRa;

    public AlunoVO(){}

    public AlunoVO(AlunoIdVO id, String alunoRa) {
        this.id = id;
        this.alunoRa = alunoRa;
    }

    public AlunoIdVO getId() {
        return id;
    }

    public void setId(AlunoIdVO id) {
        this.id = id;
    }



    public String getAlunoRa() {
        return alunoRa;
    }

    public void setAlunoRa(String alunoRa) {
        this.alunoRa = alunoRa;
    }

    @Override
    public String toString() {
        return "AlunoVO{" +
                "id=" + id +
                ", alunoRa='" + alunoRa + '\'' +
                '}';
    }
}
