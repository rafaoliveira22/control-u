package com.controlu.backend.controller;

import com.controlu.backend.service.AcessoService;
import com.controlu.backend.vo.AcessoLeituraVO;
import com.controlu.backend.vo.AcessoVO;
import com.controlu.backend.vo.AcessoVerificadorAcessoEmAbertoVO;
import com.controlu.backend.vo.AlunoVerificadorRaExistenteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/api/acesso")
public class AcessoController {
    @Autowired
    private AcessoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> obterDadosAcesso(@PathVariable("id") String id){
        AcessoVO acesso = service.obterDadosAcesso(id);
        return new ResponseEntity<>(acesso, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> obterDadosTodosAcessos(){
        List<AcessoVO> acessos = service.obterDadosTodosAcessos();
        return new ResponseEntity<>(acessos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosAcesso(@RequestBody AcessoLeituraVO acesso) {
        System.out.println("\nREGISTRAR DADOS ACESSO - AcessoController");
        System.out.println("Fuso horário da JVM: " + ZoneId.systemDefault());

        try{
            AcessoVO acessoRegistrado = service.registrarDadosAcesso(acesso);
            return new ResponseEntity<>(acessoRegistrado, HttpStatus.CREATED);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/verificarSeTemAcessoEmAberto/{ra}")
    public ResponseEntity<?> verificarSeTemAcessoEmAberto(@PathVariable("ra") String ra){
        try {
            AcessoVerificadorAcessoEmAbertoVO acessoVerificadorAcessoEmAbertoVO = new AcessoVerificadorAcessoEmAbertoVO(service.verificarSeTemAcessoEmAberto(ra));
            return new ResponseEntity<>(acessoVerificadorAcessoEmAbertoVO, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
