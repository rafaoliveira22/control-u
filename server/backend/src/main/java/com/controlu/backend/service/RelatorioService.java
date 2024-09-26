package com.controlu.backend.service;

import com.controlu.backend.entity.Presenca;
import com.controlu.backend.repository.PresencaRepository;
import com.controlu.backend.vo.FiltroRelatorioAcessoVO;
import com.controlu.backend.vo.FiltroRelatorioAulaVO;
import com.controlu.backend.vo.FiltroRelatorioPresencaVO;
import com.controlu.backend.vo.FiltroRelatorioVO;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class RelatorioService {
    @Autowired
    private PresencaRepository presencaRepository;


    public ByteArrayResource gerarRelatorio(FiltroRelatorioVO filtro) throws Exception {
        if (filtro instanceof FiltroRelatorioPresencaVO) {
            return gerarRelatorioDePresenca((FiltroRelatorioPresencaVO) filtro);
        } else if (filtro instanceof FiltroRelatorioAcessoVO) {
            return gerarRelatorioDeAcesso((FiltroRelatorioAcessoVO) filtro);
        } else if (filtro instanceof FiltroRelatorioAulaVO) {
            return gerarRelatorioDeAula((FiltroRelatorioAulaVO) filtro);
        } else {
            throw new IllegalArgumentException("Tipo de filtro não suportado");
        }
    }

    private ByteArrayResource gerarRelatorioDePresenca(FiltroRelatorioPresencaVO filtro) {
        // Aqui você acessa os campos específicos de presenças
        System.out.println("Presença ID: " + filtro.getPresencaId());
        System.out.println("Aula ID: " + filtro.getAulaId());
        return new ByteArrayResource(new byte[0]);
    }

    private ByteArrayResource gerarRelatorioDeAcesso(FiltroRelatorioAcessoVO filtro) {
        // Aqui você acessa os campos específicos de acessos
        System.out.println("Acesso ID: " + filtro.getAcessoId());
        System.out.println("Sala ID: " + filtro.getSalaId());
        return new ByteArrayResource(new byte[0]);
    }

    private ByteArrayResource gerarRelatorioDeAula(FiltroRelatorioAulaVO filtro) {
        // Aqui você acessa os campos específicos de aulas
        System.out.println("Aula ID: " + filtro.getAulaId());
        System.out.println("Sala ID: " + filtro.getSalaId());
        return new ByteArrayResource(new byte[0]);
    }

    public ByteArrayResource gerarRelatorio() throws Exception{
        // Usamos ByteArrayOutputStream para gerar o PDF em memória
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        // Configura o escritor de PDF
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        // Adiciona o título do relatório
        document.add(new Paragraph("Relatório de Presença")
                .setBold()
                .setFontSize(20));

        // Adiciona uma tabela com os dados de presença
        float[] pointColumnWidths = {100F, 100F, 150F, 150F};
        Table table = new Table(pointColumnWidths);

        // Cabeçalhos da tabela
        table.addCell(new Cell().add(new Paragraph("ID")));
        table.addCell(new Cell().add(new Paragraph("RA do Aluno")));
        table.addCell(new Cell().add(new Paragraph("Entrada")));
        table.addCell(new Cell().add(new Paragraph("Saída")));

        List<Presenca> presencas = presencaRepository.findAll();
        // Popula a tabela com os dados de presença
        for (Presenca presenca : presencas) {
            table.addCell(new Cell().add(new Paragraph(String.valueOf(presenca.getPresencaId()))));
            table.addCell(new Cell().add(new Paragraph(presenca.getAlunoId())));
            table.addCell(new Cell().add(new Paragraph(String.valueOf(presenca.getPresencaEntrada()))));
            table.addCell(new Cell().add(new Paragraph((presenca.getPresencaSaida() == null ? "Presença não registrada." : String.valueOf(presenca.getPresencaSaida())))));
        }

        // Adiciona a tabela ao documento
        document.add(table);

        // Fechamos o documento
        document.close();

        byte[] pdfData = outputStream.toByteArray();
        return new ByteArrayResource(pdfData);
    }
}
