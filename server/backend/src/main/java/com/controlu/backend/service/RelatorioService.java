package com.controlu.backend.service;

import com.controlu.backend.entity.PDFRelatorio;
import com.controlu.backend.entity.model.Acesso;
import com.controlu.backend.entity.model.Aula;
import com.controlu.backend.entity.model.Presenca;
import com.controlu.backend.repository.AcessoRepository;
import com.controlu.backend.repository.AulaRepository;
import com.controlu.backend.repository.PresencaRepository;
import com.controlu.backend.utils.DateUtils;
import com.controlu.backend.vo.*;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private DateUtils dateUtils;

    private Table table;


    public ByteArrayResource gerarRelatorio(FiltroRelatorioVO filtro) throws Exception {
        DataInicialFinalRelatorioVO dataInicialFinalRelatorioVO = new DataInicialFinalRelatorioVO(
                filtro.getDataInicial() != null ? filtro.getDataInicial().toString() : null,
                filtro.getDataFinal() != null ? filtro.getDataFinal().toString() : null,
                "dd/MM/yyyy"
        );

        filtro.setDataInicial(String.valueOf(dataInicialFinalRelatorioVO.getDataInicial()));
        filtro.setDataFinal(String.valueOf(dataInicialFinalRelatorioVO.getDataFinal()));

        if (dataInicialFinalRelatorioVO.getDataFinal().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data final não pode ser maior que a data atual.");
        }

        if (dataInicialFinalRelatorioVO.getDataInicial() != null && dataInicialFinalRelatorioVO.getDataInicial().isAfter(dataInicialFinalRelatorioVO.getDataFinal())) {
            throw new IllegalArgumentException("A data inicial não pode ser maior que a data final.");
        }

        if (filtro instanceof FiltroRelatorioPresencaVO) {
            return gerarRelatorioDePresenca((FiltroRelatorioPresencaVO) filtro, dataInicialFinalRelatorioVO);
        } else if (filtro instanceof FiltroRelatorioAcessoVO) {
            return gerarRelatorioDeAcesso((FiltroRelatorioAcessoVO) filtro, dataInicialFinalRelatorioVO);
        } else if (filtro instanceof FiltroRelatorioAulaVO) {
            return gerarRelatorioDeAula((FiltroRelatorioAulaVO) filtro, dataInicialFinalRelatorioVO);
        } else {
            throw new IllegalArgumentException("Tipo de filtro não suportado");
        }
    }

    private ByteArrayResource gerarRelatorioDePresenca(FiltroRelatorioPresencaVO filtro, DataInicialFinalRelatorioVO dataInicialFinalRelatorioVO) {
        PDFRelatorio pdfRelatorio = new PDFRelatorio("Relatório de Presença");


        table = new Table(new float[]{80F, 80F, 100F, 150F, 150F});
        adicionarParagrafoTabela("ID");
        adicionarParagrafoTabela("ID Aula");
        adicionarParagrafoTabela("R.A do Aluno");
        adicionarParagrafoTabela("Entrada");
        adicionarParagrafoTabela("Saída");

        List<Presenca> dadosRelatorio = presencaRepository.buscarPorFiltros(filtro.getAlunoId(), filtro.getAulaId(), dataInicialFinalRelatorioVO.getDataInicial(), dataInicialFinalRelatorioVO.getDataFinal());
        if(dadosRelatorio.isEmpty()){
            pdfRelatorio.getDocument().add(new Paragraph("Nenhum registro disponível."));
        } else{
            for(Presenca p : dadosRelatorio){
                adicionarParagrafoTabela(String.valueOf(p.getPresencaId()));
                adicionarParagrafoTabela(String.valueOf(p.getAulaId()));
                adicionarParagrafoTabela(p.getAlunoId());
                adicionarParagrafoTabela(dateUtils.formatarOffsetDateTimeParaString(p.getPresencaEntrada(), "dd/MM/yyyy HH:mm:ss"));
                adicionarParagrafoTabela(p.getPresencaSaida() != null ? dateUtils.formatarOffsetDateTimeParaString(p.getPresencaSaida(), "dd/MM/yyyy HH:mm:ss") : "Presença não registrada");
            }
            pdfRelatorio.adicionarTabelaNoDocumento(table);
        }

        pdfRelatorio.adicionarDataHorarioGeracaoRelatorio(dateUtils.formatarOffsetDateTimeParaString(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos(), "dd/MM/yyyy HH:mm:ss"));
        pdfRelatorio.fecharDocumento();
        return new ByteArrayResource(pdfRelatorio.obterDadosPdf());
    }

    private ByteArrayResource gerarRelatorioDeAcesso(FiltroRelatorioAcessoVO filtro, DataInicialFinalRelatorioVO dataInicialFinalRelatorioVO) {
        PDFRelatorio pdfRelatorio = new PDFRelatorio("Relatório de Acesso");


        table = new Table(new float[]{80F, 150F, 150F, 80F, 80F});
        adicionarParagrafoTabela("ID");
        adicionarParagrafoTabela("R.A Aluno");
        adicionarParagrafoTabela("ID Dispositivo");
        adicionarParagrafoTabela("Entrada");
        adicionarParagrafoTabela("Saída");

        List<Acesso> dadosRelatorio = acessoRepository.buscarPorFiltros(filtro.getAcessoId(), dataInicialFinalRelatorioVO.getDataInicial(),  dataInicialFinalRelatorioVO.getDataFinal(), filtro.getDispositivoId(), filtro.getAlunoId());
        if(dadosRelatorio.isEmpty()){
            pdfRelatorio.getDocument().add(new Paragraph("Nenhum registro disponível."));
        } else{
            for(Acesso a : dadosRelatorio){
                adicionarParagrafoTabela(String.valueOf(a.getAcessoId()));
                adicionarParagrafoTabela(String.valueOf(a.getAlunoId()));
                adicionarParagrafoTabela(a.getDispositivoId());
                adicionarParagrafoTabela(dateUtils.formatarOffsetDateTimeParaString(a.getAcessoEntrada(), "dd/MM/yyyy HH:mm:ss"));
                adicionarParagrafoTabela(dateUtils.formatarOffsetDateTimeParaString(a.getAcessoSaida(), "dd/MM/yyyy HH:mm:ss"));
            }
            pdfRelatorio.adicionarTabelaNoDocumento(table);
        }
        pdfRelatorio.adicionarDataHorarioGeracaoRelatorio(dateUtils.formatarOffsetDateTimeParaString(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos(), "dd/MM/yyyy HH:mm:ss"));
        pdfRelatorio.fecharDocumento();

        return new ByteArrayResource(pdfRelatorio.obterDadosPdf());
    }

    private ByteArrayResource gerarRelatorioDeAula(FiltroRelatorioAulaVO filtro, DataInicialFinalRelatorioVO dataInicialFinalRelatorioVO) {
        PDFRelatorio pdfRelatorio = new PDFRelatorio("Relatório de Aula");


        table = new Table(new float[]{80F, 80F, 80F, 150F, 150F});
        adicionarParagrafoTabela("ID");
        adicionarParagrafoTabela("ID Grade");
        adicionarParagrafoTabela("ID Sala");
        adicionarParagrafoTabela("Abertura");
        adicionarParagrafoTabela("Fechamento");

        List<Aula> dadosRelatorio = aulaRepository.buscarPorFiltros(filtro.getAulaId(), dataInicialFinalRelatorioVO.getDataInicial(),  dataInicialFinalRelatorioVO.getDataFinal(), filtro.getGradeId(), filtro.getSalaId());
        if(dadosRelatorio.isEmpty()){
            pdfRelatorio.getDocument().add(new Paragraph("Nenhum registro disponível."));
        } else{
            for(Aula a : dadosRelatorio){
                adicionarParagrafoTabela(String.valueOf(a.getAulaId()));
                adicionarParagrafoTabela(String.valueOf(a.getGradeId()));
                adicionarParagrafoTabela(a.getSalaId());
                adicionarParagrafoTabela(dateUtils.formatarOffsetDateTimeParaString(a.getAulaAbertura(), "dd/MM/yyyy HH:mm:ss"));
                adicionarParagrafoTabela(dateUtils.formatarOffsetDateTimeParaString(a.getAulaFechamento(), "dd/MM/yyyy HH:mm:ss"));
            }
            pdfRelatorio.adicionarTabelaNoDocumento(table);
        }
        pdfRelatorio.adicionarDataHorarioGeracaoRelatorio(dateUtils.formatarOffsetDateTimeParaString(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos(), "dd/MM/yyyy HH:mm:ss"));
        pdfRelatorio.fecharDocumento();

        return new ByteArrayResource(pdfRelatorio.obterDadosPdf());
    }

    private void adicionarParagrafoTabela(String texto){
        table.addCell(new Cell().add(new Paragraph(texto)));
    }
}
