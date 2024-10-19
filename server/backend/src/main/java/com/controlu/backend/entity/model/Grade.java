package com.controlu.backend.entity.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {
    @Id
    @Column(name = "grade_id")
    private String gradeId;

    @Column(name = "curso_id")
    private Integer cursoId;

    @Column(name = "disciplina_id")
    private String disciplinaId;

    @Column(name = "professor_id")
    private String professorId;

    @Column(name = "cartao_id")
    private String cartaoId;

    public Grade(){}

    public Grade(String gradeId, Integer cursoId, String disciplinaId, String professorId, String cartaoId) {
        this.gradeId = gradeId;
        this.cursoId = cursoId;
        this.disciplinaId = disciplinaId;
        this.professorId = professorId;
        this.cartaoId = cartaoId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
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
        return "Grade{" +
                "gradeId=" + gradeId +
                ", cursoId=" + cursoId +
                ", disciplinaId='" + disciplinaId + '\'' +
                ", professorId='" + professorId + '\'' +
                ", cartaoId='" + cartaoId + '\'' +
                '}';
    }
}
