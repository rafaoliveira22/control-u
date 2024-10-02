package com.controlu.backend.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateUtils {
    public OffsetDateTime obterDataHoraAtualSemPrecisaoDeSegundos() {
        return OffsetDateTime.now().withNano(0);
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
