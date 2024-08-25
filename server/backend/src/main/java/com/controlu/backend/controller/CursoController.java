package com.controlu.backend.controller;

import com.controlu.backend.service.CursoService;
import com.controlu.backend.vo.CursoVO;
import com.controlu.backend.vo.aluno.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {
    @Autowired
    private CursoService service;

    @GetMapping
    public ResponseEntity<?> obterTodosCursos(){
        try {
            List<CursoVO> cursos = service.obterTodosCursos();
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{cursoId}")
    public ResponseEntity<?> obterCurso(@PathVariable("cursoId") String cursoId) {
        try {
            return new ResponseEntity<>(service.obterCurso(cursoId), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosCurso(@RequestBody CursoVO curso){
        try{
            CursoVO cursoRegistrado = service.registrarDadosCurso(curso);
            return new ResponseEntity<>(cursoRegistrado, HttpStatus.CREATED);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
