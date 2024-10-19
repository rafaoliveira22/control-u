package com.controlu.backend.controller;

import com.controlu.backend.service.PresencaService;
import com.controlu.backend.vo.AulaVO;
import com.controlu.backend.vo.PresencaLeituraVO;
import com.controlu.backend.vo.PresencaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presenca")
public class PresencaController {
    @Autowired
    private PresencaService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> obterDadosPresenca(@PathVariable("id") String id){
        PresencaVO presenca = service.obterDadosPresenca(id);
        return new ResponseEntity<>(presenca, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> obterDadosTodasPresencas(){
        List<PresencaVO> presencas = service.obterDadosTodasPresencas();
        return new ResponseEntity<>(presencas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosPresenca(@RequestBody PresencaLeituraVO presenca){
        PresencaVO presencaRegistrada = service.registrarDadosPresenca(presenca);
        return new ResponseEntity<>(presencaRegistrada, HttpStatus.CREATED);
    }
}
