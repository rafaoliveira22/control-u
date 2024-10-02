package com.controlu.backend.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DataInicialFinalRelatorioVO {
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    public DataInicialFinalRelatorioVO(){}

    public DataInicialFinalRelatorioVO(LocalDate dataInicial, LocalDate dataFinal) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public DataInicialFinalRelatorioVO(String dataInicialString, String dataFinalString, String formato){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

        LocalDate dataInicialFormatada = Optional.ofNullable(dataInicialString)
                .map(data -> LocalDate.parse(data, formatter))
                .orElse(null);

        LocalDate dataFinalFormatada = Optional.ofNullable(dataFinalString)
                .map(data -> LocalDate.parse(data, formatter))
                .orElse(LocalDate.now());


        this.dataInicial = dataInicialFormatada;
        this.dataFinal = dataFinalFormatada;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
}
