package com.controlu.backend.controller;

import com.controlu.backend.service.AlunoService;
import com.controlu.backend.vo.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/all")
    public List<AlunoVO> obterDadosDeTodosAlunos(){
        return alunoService.obterDadosDeTodosAlunos();
    }

    @GetMapping("/{id}")
    public AlunoVO obterDadosAluno(@PathVariable("id") String id){
        return alunoService.obterDadosAluno(id);
    }


    @PostMapping()
    public AlunoVO registrarDadosAluno(@RequestBody AlunoVO aluno){
        return alunoService.registrarDadosAluno(aluno);
    }

    @PutMapping()
    public AlunoVO atualizarDadosAluno(@RequestBody AlunoVO aluno){
        return alunoService.atualizarDadosAluno(aluno);
    }

    @DeleteMapping("/{id}")
    public void apagarDadosAlunos(@PathVariable("id") String id){
        alunoService.apagarDadosAlunos(id);
    }
}
