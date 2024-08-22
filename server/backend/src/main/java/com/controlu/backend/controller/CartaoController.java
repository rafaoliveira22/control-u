package com.controlu.backend.controller;


import com.controlu.backend.service.CartaoLeituraService;
import com.controlu.backend.vo.CartaoLeituraVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {
    @Autowired
    private CartaoLeituraService service;

    @GetMapping
    public ResponseEntity<?> obterDadosTodosCartoes(){
        try {
            List<CartaoLeituraVO> cartoes = service.obterDadosTodosCartoes();
            return new ResponseEntity<>(cartoes, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterDadosCartao(@PathVariable("id") String id){
        try {
            CartaoLeituraVO cartao = service.obterDadosCartao(id);
            return new ResponseEntity<>(cartao, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosCartao(){
        try {
            CartaoLeituraVO cartaoRegistrado = service.registrarDadosCartao();
            return new ResponseEntity<>(cartaoRegistrado, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
