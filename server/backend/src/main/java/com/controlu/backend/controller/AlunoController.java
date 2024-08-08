package com.controlu.backend.controller;

import com.controlu.backend.service.AlunoService;
import com.controlu.backend.vo.aluno.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<AlunoVO> obterDadosDeTodosAlunos(){
        return alunoService.obterDadosDeTodosAlunos();
    }

    @GetMapping("/{nome}/{cursoId}/{anoIngressao}")
    public AlunoVO obterDadosAluno(@PathVariable("nome") String nome, @PathVariable("cursoId") String cursoId, @PathVariable("anoIngressao") String anoIngressao){
        return alunoService.obterDadosAluno(nome, cursoId, anoIngressao);
    }

    @GetMapping("/{ra}")
    public AlunoVO obterDadosAluno(@PathVariable("ra") String ra){
        return alunoService.obterDadosAluno(ra);
    }


    @PostMapping()
    public ResponseEntity<?> registrarDadosAluno(@RequestBody AlunoVO aluno){
        try {
            AlunoVO alunoRegistrado = alunoService.registrarDadosAluno(aluno);
            return new ResponseEntity<>(alunoRegistrado, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping()
    public AlunoVO atualizarDadosAluno(@RequestBody AlunoVO aluno){
        return alunoService.atualizarDadosAluno(aluno);
    }

    @DeleteMapping("/{ra}")
    public void apagarDadosAlunos(@PathVariable("ra") String ra){
        alunoService.apagarDadosAlunos(ra);
    }
}
