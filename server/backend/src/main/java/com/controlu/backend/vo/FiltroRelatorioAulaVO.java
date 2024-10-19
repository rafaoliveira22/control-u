package com.controlu.backend.vo;

public class FiltroRelatorioAulaVO extends FiltroRelatorioVO {
    private String aulaId;
    private String gradeId;
    private String salaId;
    private String professorId;


    public FiltroRelatorioAulaVO(){}

    public FiltroRelatorioAulaVO(String aulaId, String gradeId, String salaId, String professorId, String alunoId) {
        this.aulaId = aulaId;
        this.gradeId = gradeId;
        this.salaId = salaId;
        this.professorId = professorId;
    }

    public FiltroRelatorioAulaVO(String dataInicial, String dataFinal, String tipo, String aulaId, String gradeId, String salaId, String professorId) {
        super(dataInicial, dataFinal, tipo);
        this.aulaId = aulaId;
        this.gradeId = gradeId;
        this.salaId = salaId;
        this.professorId = professorId;
    }

    public String getAulaId() {
        return aulaId;
    }

    public void setAulaId(String aulaId) {
        this.aulaId = aulaId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public String getSalaId() {
        return salaId;
    }

    public void setSalaId(String salaId) {
        this.salaId = salaId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }
}
