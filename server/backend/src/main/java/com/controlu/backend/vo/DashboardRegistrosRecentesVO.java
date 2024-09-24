package com.controlu.backend.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;


public class DashboardRegistrosRecentesVO extends RepresentationModel<DashboardRegistrosRecentesVO> {
    private String data;

    private String horario;
    private String descricao;

    public DashboardRegistrosRecentesVO(){}

    public DashboardRegistrosRecentesVO(String data, String horario, String descricao) {
        this.data = data;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
