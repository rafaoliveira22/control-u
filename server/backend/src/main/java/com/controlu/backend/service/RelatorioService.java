package com.controlu.backend.service;

import com.controlu.backend.entity.PDFRelatorio;
import com.controlu.backend.entity.model.Presenca;
import com.controlu.backend.repository.PresencaRepository;
import com.controlu.backend.utils.DateUtils;
import com.controlu.backend.vo.*;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    private PresencaRepository presencaRepository;

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
            return gerarRelatorioDeAcesso((FiltroRelatorioAcessoVO) filtro);
        } else if (filtro instanceof FiltroRelatorioAulaVO) {
            return gerarRelatorioDeAula((FiltroRelatorioAulaVO) filtro);
        } else {
            throw new IllegalArgumentException("Tipo de filtro não suportado");
        }
    }

    private ByteArrayResource gerarRelatorioDePresenca(FiltroRelatorioPresencaVO filtro, DataInicialFinalRelatorioVO dataInicialFinalRelatorioVO) {
        PDFRelatorio pdfRelatorio = new PDFRelatorio("Relatório de Presença");


        table = new Table(new float[]{80F, 80F, 100F, 150F, 150F});
        adicionarParagrafoTabela("ID");
        adicionarParagrafoTabela("ID Aula");
        adicionarParagrafoTabela("RA do Aluno");
        adicionarParagrafoTabela("Entrada");
        adicionarParagrafoTabela("Saída");

        List<Presenca> dadosRelatorio = presencaRepository.buscarPorFiltros(filtro.getAlunoId(), filtro.getAulaId(), dataInicialFinalRelatorioVO.getDataInicial(), dataInicialFinalRelatorioVO.getDataFinal());
        for(Presenca p : dadosRelatorio){
            adicionarParagrafoTabela(String.valueOf(p.getPresencaId()));
            adicionarParagrafoTabela(String.valueOf(p.getAulaId()));
            adicionarParagrafoTabela(p.getAlunoId());
            adicionarParagrafoTabela(dateUtils.formatarOffsetDateTimeParaString(p.getPresencaEntrada(), "dd/MM/yyyy HH:mm:ss"));
            adicionarParagrafoTabela(p.getPresencaSaida() != null ? dateUtils.formatarOffsetDateTimeParaString(p.getPresencaSaida(), "dd/MM/yyyy HH:mm:ss") : "Presença não registrada");
        }
        pdfRelatorio.adicionarTabelaNoDocumento(table);
        pdfRelatorio.fecharDocumento();

        return new ByteArrayResource(pdfRelatorio.obterDadosPdf());
    }

    private ByteArrayResource gerarRelatorioDeAcesso(FiltroRelatorioAcessoVO filtro) {
        return new ByteArrayResource(new byte[0]);
    }

    private ByteArrayResource gerarRelatorioDeAula(FiltroRelatorioAulaVO filtro) {
        return new ByteArrayResource(new byte[0]);
    }

    private void adicionarParagrafoTabela(String texto){
        table.addCell(new Cell().add(new Paragraph(texto)));
    }
}
