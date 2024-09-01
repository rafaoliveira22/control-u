package com.controlu.backend.vo;


import org.springframework.hateoas.RepresentationModel;

public class GradeVO extends RepresentationModel<GradeVO> {
    private Integer gradeId;

    private Integer cursoId;

    private String disciplinaId;

    private String professorId;

    private String cartaoId;

    public GradeVO(){}

    public GradeVO(Integer gradeId, Integer cursoId, String disciplinaId, String professorId, String cartaoId) {
        this.gradeId = gradeId;
        this.cursoId = cursoId;
        this.disciplinaId = disciplinaId;
        this.professorId = professorId;
        this.cartaoId = cartaoId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getCursoId() {
        return cursoId;
    }

    public void setCursoId(Integer cursoId) {
        this.cursoId = cursoId;
    }

    public String getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(String disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getCartaoId() {
        return cartaoId;
    }

    public void setCartaoId(String cartaoId) {
        this.cartaoId = cartaoId;
    }

    @Override
    public String toString() {
        return "GradeVO{" +
                "gradeId=" + gradeId +
                ", cursoId=" + cursoId +
                ", disciplinaId='" + disciplinaId + '\'' +
                ", professorId='" + professorId + '\'' +
                ", cartaoId='" + cartaoId + '\'' +
                '}';
    }
}
