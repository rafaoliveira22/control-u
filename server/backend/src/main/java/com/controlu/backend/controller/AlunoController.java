package com.controlu.backend.controller;

import com.controlu.backend.service.AlunoService;
import com.controlu.backend.vo.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/all")
    public List<AlunoVO> obterDadosDeTodosAlunoa(){
        return alunoService.obterDadosDeTodosAlunoa();
    }

    @GetMapping(value = "/")
    public AlunoVO obterDadosAluno(@PathVariable String id){
        return alunoService.obterDadosAluno();
    }


    @PostMapping(value = "/")
    public AlunoVO registrarDadosAluno(@RequestBody AlunoVO aluno){
        return alunoService.registrarDadosAluno();
    }

    @PutMapping(value = "/")
    public AlunoVO atualizarDadosAluno(@RequestBody AlunoVO aluno){
        return alunoService.atualizarDadosAluno();
    }

    @DeleteMapping(value = "/{id}")
    public void apagarDadosAlunos(@PathVariable String id){
        alunoService.apagarDadosAlunos();
    }
}
