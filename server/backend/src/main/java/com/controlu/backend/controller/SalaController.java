package com.controlu.backend.controller;

import com.controlu.backend.service.SalaService;
import com.controlu.backend.vo.SalaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sala")
public class SalaController {
    @Autowired
    private SalaService service;

    @GetMapping
    public ResponseEntity<?> obterTodasSalas(){
        try{
            List<SalaVO> salas = service.obterTodasSalas();
            return new ResponseEntity<>(salas, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterSala(@PathVariable("id") String id){
        try{
            SalaVO sala = service.obterSala(id);
            return new ResponseEntity<>(sala, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosSala(@RequestBody SalaVO sala){
        try{
            SalaVO salaRegistrada = service.registrarDadosSala(sala);
            return new ResponseEntity<>(salaRegistrada, HttpStatus.CREATED);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
