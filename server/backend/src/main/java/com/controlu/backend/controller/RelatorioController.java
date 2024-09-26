package com.controlu.backend.controller;

import com.controlu.backend.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {
    @Autowired
    private RelatorioService service;
    @GetMapping
    public ResponseEntity<ByteArrayResource> gerarRelatorio() throws Exception {
        ByteArrayResource resource = service.gerarRelatorio();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-presenca.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
