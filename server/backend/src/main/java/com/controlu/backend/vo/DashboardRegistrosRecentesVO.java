package com.controlu.backend.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalTime;


public class DashboardRegistrosRecentesVO extends RepresentationModel<DashboardRegistrosRecentesVO> {
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDate data;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horario;
    private String descricao;

    public DashboardRegistrosRecentesVO(){}

    public DashboardRegistrosRecentesVO(String descricao) {
        this.data = LocalDate.now();
        this.horario = LocalTime.now();
        this.descricao = descricao;
    }

    public DashboardRegistrosRecentesVO(LocalDate data, LocalTime horario, String descricao) {
        this.data = data;
        this.horario = horario;
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
