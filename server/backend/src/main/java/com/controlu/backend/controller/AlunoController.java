package com.controlu.backend.controller;

import com.controlu.backend.service.AlunoService;
import com.controlu.backend.vo.AlunoCadastroVO;
import com.controlu.backend.vo.AlunoVO;
import com.controlu.backend.vo.AlunoVerificadorRaExistenteVO;
import jakarta.validation.Valid;
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
    public ResponseEntity<?> obterDadosDeTodosAlunos(){
        try {
            List<AlunoVO> alunos = alunoService.obterDadosDeTodosAlunos();
            return new ResponseEntity<>(alunos, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{ra}")
    public ResponseEntity<?> obterDadosAluno(@PathVariable("ra") String ra){
        try {
            AlunoVO aluno = alunoService.obterDadosAluno(ra);
            return new ResponseEntity<>(aluno, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @PostMapping()
    public ResponseEntity<?> registrarDadosAluno(@RequestBody AlunoCadastroVO aluno){
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

    @GetMapping("/verificarSeEstaRegistrado/{ra}")
    public ResponseEntity<?> verificarSeEstaRegistrado(@PathVariable("ra") String ra){
        try {
            AlunoVerificadorRaExistenteVO alunoVerificadorRaExistenteVO = new AlunoVerificadorRaExistenteVO(alunoService.verificarSeEstaRegistrado(ra));
            return new ResponseEntity<>(alunoVerificadorRaExistenteVO, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
