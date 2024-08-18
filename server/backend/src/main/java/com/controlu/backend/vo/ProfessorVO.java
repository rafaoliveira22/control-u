package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class ProfessorVO extends RepresentationModel<ProfessorVO> {
    private String professorId;
    private String professorNome;

    public ProfessorVO(){}

    public ProfessorVO(String professorId, String professorNome){
        this.professorId = professorId;
        this.professorNome = professorNome;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getProfessorNome() {
        return professorNome;
    }

    public void setProfessorNome(String professorNome) {
        this.professorNome = professorNome;
    }

    @Override
    public String toString() {
        return "ProfessorVO{" +
                "professorId='" + professorId + '\'' +
                ", professorNome='" + professorNome + '\'' +
                '}';
    }
}
