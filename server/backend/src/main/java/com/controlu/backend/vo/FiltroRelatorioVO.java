package com.controlu.backend.vo;

import com.controlu.backend.utils.Defines;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Os filtros controlam as informações que aparecem no relatório
 * *
 * DATAS
 * As datas controlam o espaço entre os registros.
 * - Caso não tenha data no filtro, ou seja, venha
 * como null, serão considerados todos os registros.
 * - Caso tenha dataInicial e não tenha dataFinal, serão considerados os registros a partir da dataInicial e menores
 * que a data atual, ou seja, a data atual será considerada a dataFinal.
 * - Caso tenha dataInicial e tenha dataFinal, serão considerados os registros a partir da dataInicial até a dataFinal,
 * lembrando que a dataFinal nunca pode ser maior que a dataAtual, e consequentemente, a dataInical não pode ser menor que a dataFinal
 * *
 * TIPO
 * O tipo do relatório é se ele é
 * - ACESSO
 *   - acessoId
 *   - alunoId
 *   - dispositivoId
 *   - dataInicial
 *   - dataFinal
 * - PRESENÇA
 *   - presencaId
 *   - alunoId
 *   - aulaId
 *   - gradeId
 *   - dataInicial
 *   - dataFinal
 * - AULA
 *   - aulaId
 *   - gradeId
 *   - professorId
 *   - salaId
 *   - dataInicial
 *   - dataFinal
 * *
 */


/**
 * Deserialização Polimórfica
 *
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FiltroRelatorioPresencaVO.class, name = Defines.TIPO_RELATORIO_PRESENCA),
        @JsonSubTypes.Type(value = FiltroRelatorioAcessoVO.class, name = Defines.TIPO_RELATORIO_ACESSO),
        @JsonSubTypes.Type(value = FiltroRelatorioAulaVO.class, name = Defines.TIPO_RELATORIO_AULA)
})
public class FiltroRelatorioVO {
    private String dataInicial;
    private String dataFinal;
    private String tipo;


    public FiltroRelatorioVO(){}

    public FiltroRelatorioVO(String dataInicial, String dataFinal, String tipo) {
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.tipo = tipo;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "FiltroRelatorioVO{" +
                "dataInicial='" + dataInicial + '\'' +
                ", dataFinal='" + dataFinal + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
