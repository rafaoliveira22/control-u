package com.controlu.backend.entity;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayOutputStream;

public class PDFRelatorio {
    private ByteArrayOutputStream outputStream;
    private PdfWriter writer;
    private PdfDocument pdfDoc;
    private Document document;

    /**
     * MÉTODO CONSTRUTOR PARA INICIALIZAR O PDF
     * 1. GERAR O PDF EM MEMORIA
     * *  outputStream
     * 2. CONFIGURAR O ESCRITOR DE PDF
     * *  writer
     * *  pdfDoc
     * *  document
     */
    public PDFRelatorio(String titulo) {
        this.outputStream = new ByteArrayOutputStream();
        this.writer = new PdfWriter(outputStream);
        this.pdfDoc = new PdfDocument(writer);
        this.document = new Document(pdfDoc);
        this.document.add(new Paragraph(titulo)
                .setBold()
                .setFontSize(20));

    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public PdfWriter getWriter() {
        return writer;
    }

    public void setWriter(PdfWriter writer) {
        this.writer = writer;
    }

    public PdfDocument getPdfDoc() {
        return pdfDoc;
    }

    public void setPdfDoc(PdfDocument pdfDoc) {
        this.pdfDoc = pdfDoc;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public void adicionarTabelaNoDocumento(Table table){
        document.add(table);
    }

    public void fecharDocumento(){
        document.close();
    }

    public byte[] obterDadosPdf(){
        return outputStream.toByteArray();
    }
}
