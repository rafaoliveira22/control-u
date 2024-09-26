package com.controlu.backend.vo;

public class FiltroRelatorioAulaVO extends FiltroRelatorioVO {
    private Integer aulaId;
    private Integer gradeId;
    private String salaId;
    private String professorId;

    public FiltroRelatorioAulaVO(){}

    public FiltroRelatorioAulaVO(Integer aulaId, Integer gradeId, String salaId, String professorId) {
        this.aulaId = aulaId;
        this.gradeId = gradeId;
        this.salaId = salaId;
        this.professorId = professorId;
    }

    public FiltroRelatorioAulaVO(String dataInicial, String dataFinal, String tipo, String alunoId, Integer aulaId, Integer gradeId, String salaId, String professorId) {
        super(dataInicial, dataFinal, tipo, alunoId);
        this.aulaId = aulaId;
        this.gradeId = gradeId;
        this.salaId = salaId;
        this.professorId = professorId;
    }

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
        this.aulaId = aulaId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
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
