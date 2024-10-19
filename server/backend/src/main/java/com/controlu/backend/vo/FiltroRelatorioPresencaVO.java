package com.controlu.backend.vo;

public class FiltroRelatorioPresencaVO extends FiltroRelatorioVO{
    private Integer presencaId;
    private String aulaId;
    private String gradeId;

    private String alunoId;

    public FiltroRelatorioPresencaVO(){}

    public FiltroRelatorioPresencaVO(String dataInicial, String dataFinal, String tipo, String alunoId, Integer presencaId, String aulaId, String gradeId) {
        super(dataInicial, dataFinal, tipo);
        this.alunoId = alunoId;
        this.presencaId = presencaId;
        this.aulaId = aulaId;
        this.gradeId = gradeId;
    }

    public Integer getPresencaId() {
        return presencaId;
    }

    public void setPresencaId(Integer presencaId) {
        this.presencaId = presencaId;
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

    public String getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(String alunoId) {
        this.alunoId = alunoId;
    }

    @Override
    public String toString() {
        return "FiltroRelatorioPresencaVO{" +
                "presencaId=" + presencaId +
                ", aulaId=" + aulaId +
                ", gradeId='" + gradeId + '\'' +
                '}';
    }
}
