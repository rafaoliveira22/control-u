package com.controlu.backend.controller;

import com.controlu.backend.service.RelatorioService;
import com.controlu.backend.vo.FiltroRelatorioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/relatorio")
public class RelatorioController {
    @Autowired
    private RelatorioService service;
    @PostMapping
    public ResponseEntity<ByteArrayResource> gerarRelatorio(@RequestBody FiltroRelatorioVO filtro) throws Exception {
        ByteArrayResource resource = service.gerarRelatorio(filtro);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=relatorio-presenca.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
