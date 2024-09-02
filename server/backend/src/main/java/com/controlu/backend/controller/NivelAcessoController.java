package com.controlu.backend.controller;

import com.controlu.backend.service.NivelAcessoService;
import com.controlu.backend.vo.NivelAcessoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nivelacesso")
public class NivelAcessoController {
    @Autowired
    private NivelAcessoService service;

    @GetMapping
    public ResponseEntity<?> obterDadosTodosNiveisDeAcesso(){
        try{
            List<NivelAcessoVO> niveisDeAcesso = service.obterDadosTodosNiveisDeAcesso();
            return new ResponseEntity<>(niveisDeAcesso, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterDadosNivelDeAcesso(@PathVariable("id") String id){
        try{
            NivelAcessoVO nivelDeAcesso = service.obterDadosNivelDeAcesso(id);
            return new ResponseEntity<>(nivelDeAcesso, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
