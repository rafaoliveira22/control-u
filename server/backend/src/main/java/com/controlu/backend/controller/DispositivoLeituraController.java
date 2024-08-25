package com.controlu.backend.controller;

import com.controlu.backend.service.DispositivoLeituraService;
import com.controlu.backend.vo.DispositivoLeituraVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivo")
public class DispositivoLeituraController {
    @Autowired
    private DispositivoLeituraService service;

    @GetMapping
    public ResponseEntity<?> obterDadosDeTodosDispositivos(){
        try {
            List<DispositivoLeituraVO> dispositivos = service.obterDadosDeTodosDispositivos();
            return new ResponseEntity<>(dispositivos, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<?> obterDadosDeTodosDispositivosPorStatus(@PathVariable("status") String status){
        try {
            List<DispositivoLeituraVO> dispositivos = service.obterDadosDeTodosDispositivosPorStatus(status);
            return new ResponseEntity<>(dispositivos, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterDadosDispositivo(@PathVariable("id") String id){
        try {
            DispositivoLeituraVO dispositivo = service.obterDadosDispositivo(id);
            return new ResponseEntity<>(dispositivo, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosDispositivo(@RequestBody DispositivoLeituraVO dispositivo){
        try {
            DispositivoLeituraVO dispositivoRegistrado = service.registrarDadosDispositivo(dispositivo);
            return new ResponseEntity<>(dispositivoRegistrado, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
