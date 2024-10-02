package com.controlu.backend.vo;

public class FiltroRelatorioPresencaVO extends FiltroRelatorioVO{
    private Integer presencaId;
    private Integer aulaId;
    private String gradeId;

    public FiltroRelatorioPresencaVO(){}

    public FiltroRelatorioPresencaVO(String dataInicial, String dataFinal, String tipo, String alunoId, Integer presencaId, Integer aulaId, String gradeId) {
        super(dataInicial, dataFinal, tipo, alunoId);
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

    public Integer getAulaId() {
        return aulaId;
    }

    public void setAulaId(Integer aulaId) {
        this.aulaId = aulaId;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
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
