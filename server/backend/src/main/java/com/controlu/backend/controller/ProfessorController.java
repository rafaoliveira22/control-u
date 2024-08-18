package com.controlu.backend.controller;

import com.controlu.backend.service.ProfessorService;
import com.controlu.backend.vo.CursoVO;
import com.controlu.backend.vo.ProfessorVO;
import com.controlu.backend.vo.aluno.AlunoVO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService service;

    @GetMapping
    public ResponseEntity<?> obterTodosProfessores(){
        try {
            List<ProfessorVO> professores = service.obterTodosProfessores();
            return new ResponseEntity<>(professores, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterDadosProfessor(@PathVariable("id") String id){
        try {
            ProfessorVO professor = service.obterDadosProfessor(id);
            return new ResponseEntity<>(professor, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> registrarDadosProfessor(@RequestBody ProfessorVO professor){
        try {
            ProfessorVO professorRegistrado = service.registrarDadosProfessor(professor);
            return new ResponseEntity<>(professorRegistrado, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
