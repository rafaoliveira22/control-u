package com.controlu.backend.utils;

public class Defines {

    // DISPOSITIVO DE LEITURA
    public static final Integer TIPO_DISPOSITIVO_CATRACA = 1;
    public static final Integer TIPO_DISPOSITIVO_TOTEM = 2;
    public static final String PREFIXO_ID_TIPO_DISPOSITIVO_CATRACA = "CAT";
    public static final String PREFIXO_ID_TIPO_DISPOSITIVO_TOTEM = "TL";
    public static final Integer STATUS_DISPOSITIVO_ONLINE = 1;
    public static final Integer STATUS_DISPOSITIVO_OFFLINE = 2;

    // CARTÃO DE LEITURA
    public static final Integer STATUS_CARTAO_EM_USO = 1;
    public static final Integer STATUS_CARTAO_SEM_USO = 2;

    // SALA
    public static final Integer TIPO_SALA_SALA = 1;
    public static final Integer TIPO_SALA_LABORATORIO = 2;
    public static final String PREFIXO_ID_TIPO_SALA = "SL";
    public static final String PREFIXO_ID_TIPO_SALA_LABORATORIO = "LAB";

    // TIPOS DE USUÁRIO (NIVEL DE ACESSO)
    public static final Integer ROLE_USUARIO_ADM = 1;
    public static final Integer ROLE_USUARIO_COMUM = 2;
    public static final Integer ROLE_USUARIO_OPERADOR = 3;

    // JWT
    public static final String ISSUER_JWT = "ControlU Backend API";

    // DASHBOARD
    // TIPOS REGISTROS RECENTES
    public static final String TIPO_REGISTRO_RECENTE_ACESSO_ENTRADA = "ACESSO_ENTRADA";
    public static final String TIPO_REGISTRO_RECENTE_ACESSO_SAIDA = "ACESSO_SAIDA";
    public static final String TIPO_REGISTRO_RECENTE_AULA_ABERTURA = "AULA_ABERTURA";
    public static final String TIPO_REGISTRO_RECENTE_AULA_FECHAMENTO = "AULA_FECHAMENTO";
    public static final String TIPO_REGISTRO_RECENTE_PRESENCA_ENTRADA = "PRESENCA_ENTRADA";
    public static final String TIPO_REGISTRO_RECENTE_PRESENCA_SAIDA = "PRESENCA_SAIDA";


    // RELATÓRIO
    //TIPO
    public static final String TIPO_RELATORIO_ACESSO = "ACESSO";
    public static final String TIPO_RELATORIO_AULA = "AULA";
    public static final String TIPO_RELATORIO_PRESENCA = "PRESENÇA";

    // TITULO
    public static final String TITULO_RELAORIO_ACESSO = "Relatório de Acesso";
    public static final String TITULO_RELAORIO_AULA = "Relatório de Aula";
    public static final String TITULO_RELAORIO_PRESENCA = "Relatório de Presença";
}
