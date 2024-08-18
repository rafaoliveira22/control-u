package com.controlu.backend.controller;

import com.controlu.backend.service.DisciplinaService;
import com.controlu.backend.service.ProfessorService;
import com.controlu.backend.vo.DisciplinaVO;
import com.controlu.backend.vo.ProfessorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplina")
public class DisciplinaController {
    @Autowired
    private DisciplinaService service;

    @GetMapping
    public ResponseEntity<?> obterDadosDeTodasDisciplinas(){
        try {
            List<DisciplinaVO> disciplinas = service.obterDadosDeTodasDisciplinas();
            return new ResponseEntity<>(disciplinas, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterDadosDisciplina(@PathVariable("id") String id){
        try {
            DisciplinaVO disciplina = service.obterDadosDisciplina(id);
            return new ResponseEntity<>(disciplina, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> registrarDadosDisciplina(@RequestBody DisciplinaVO disciplina){
        try {
            DisciplinaVO disciplinaRegistrada = service.registrarDadosDiscplina(disciplina);
            return new ResponseEntity<>(disciplinaRegistrada, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
