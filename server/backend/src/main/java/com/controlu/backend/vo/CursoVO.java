package com.controlu.backend.vo;

import org.springframework.hateoas.RepresentationModel;

public class CursoVO extends RepresentationModel<CursoVO> {
    private Integer cursoId;
    private String cursoNome;


    public CursoVO(){}

    public CursoVO(Integer cursoId, String cursoNome) {
        this.cursoId = cursoId;
        this.cursoNome = cursoNome;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getCursoNome() {
        return cursoNome;
    }

    public void setCursoNome(String cursoNome) {
        this.cursoNome = cursoNome;
    }

    @Override
    public String toString() {
        return "CursoVO{" +
                "id=" + cursoId +
                ", nome='" + cursoNome + '\'' +
                '}';
    }
}
