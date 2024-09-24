package com.controlu.backend.controller;

import com.controlu.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService service;


    @GetMapping
    public ResponseEntity<?> obterDadosDashboard(){
        return new ResponseEntity<>(service.obterDadosDashboard(), HttpStatus.OK);
    }

    @GetMapping("/aluno/aula")
    public ResponseEntity<?> obterDadosAlunosAula(){
        return new ResponseEntity<>(service.obterDadosAlunosAula(), HttpStatus.OK);
    }

    @GetMapping("/aluno/acesso")
    public ResponseEntity<?> obterDadosAlunosAcesso(){
        return new ResponseEntity<>(service.obterDadosAlunosAcesso(), HttpStatus.OK);
    }

    @GetMapping("/aula")
    public ResponseEntity<?> obterDadosAula(){
        return new ResponseEntity<>(service.obterDadosAula(), HttpStatus.OK);
    }

    @GetMapping("/recentes")
    public ResponseEntity<?> obterDadosRegistrosRecentes(){
        return new ResponseEntity<>(service.obterDadosRegistrosRecentes(), HttpStatus.OK);
    }

}
