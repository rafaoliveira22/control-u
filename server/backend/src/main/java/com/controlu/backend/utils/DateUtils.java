package com.controlu.backend.utils;

import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;

@Service
public class DateUtils {
    /*public OffsetDateTime obterDataHoraAtualSemPrecisaoDeSegundos() {
        return OffsetDateTime.now().withNano(0);
    } */

    /*public OffsetDateTime obterDataHoraAtualSemPrecisaoDeSegundos() {
        // Define o fuso horário para São Paulo (ou ajuste conforme necessário)
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));

        // Converte para OffsetDateTime e remove os nanossegundos
        return zonedDateTime.toOffsetDateTime().withNano(0);
    } */

    public OffsetDateTime obterDataHoraAtualSemPrecisaoDeSegundos() {
        // Obtem a data/hora local e converte para UTC
        OffsetDateTime dataHoraLocal = OffsetDateTime.now(ZoneId.of("America/Sao_Paulo"));
        return dataHoraLocal.withOffsetSameInstant(ZoneOffset.UTC).withNano(0);
    }

    /**
     * @param data
     * @param formato ("yyyy-MM-dd HH:mm:ss")
     * @return
     */
    public String formatatarLocalDateTimeParaString(LocalDateTime data, String formato){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return data.format(formatter);
    }

    public String formatarOffsetDateTimeParaString(OffsetDateTime data, String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato); //"dd/MM/yyyy HH:mm:ss"
        return data.format(formatter);
    }

}
