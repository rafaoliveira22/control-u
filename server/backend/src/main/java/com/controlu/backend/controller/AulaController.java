package com.controlu.backend.controller;

import com.controlu.backend.service.AulaService;
import com.controlu.backend.vo.AcessoVO;
import com.controlu.backend.vo.AulaLeituraVO;
import com.controlu.backend.vo.AulaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aula")
public class AulaController {
    @Autowired
    private AulaService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> obterDadosAula(@PathVariable("id") String id){
        AulaVO aula = service.obterDadosAula(id);
        return new ResponseEntity<>(aula, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> obterDadosTodasAulas(){
        List<AulaVO> aulas = service.obterDadosTodasAulas();
        return new ResponseEntity<>(aulas.isEmpty() ? "Nenhuma aula disponível" : aulas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosAula(@RequestBody AulaLeituraVO aula){
        AulaVO aulaRegistrada = service.registrarDadosAula(aula);
        return new ResponseEntity<>(aulaRegistrada, HttpStatus.CREATED);
    }
}
