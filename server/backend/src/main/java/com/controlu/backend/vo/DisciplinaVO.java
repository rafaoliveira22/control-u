package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class DisciplinaVO extends RepresentationModel<DisciplinaVO> {
    private String disciplinaId;


    private String disciplinaNome;

    public DisciplinaVO(){}

    public DisciplinaVO(String disciplinaId, String disciplinaNome) {
        this.disciplinaId = disciplinaId;
        this.disciplinaNome = disciplinaNome;
    }

    public String getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(String disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    @Override
    public String toString() {
        return "DisciplinaVO{" +
                "disciplinaId='" + disciplinaId + '\'' +
                ", disciplinaNome='" + disciplinaNome + '\'' +
                '}';
    }
}
