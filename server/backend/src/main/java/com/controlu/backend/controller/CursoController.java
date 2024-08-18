package com.controlu.backend.controller;

import com.controlu.backend.service.CursoService;
import com.controlu.backend.vo.CursoVO;
import com.controlu.backend.vo.aluno.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public CursoVO obterCurso(@PathVariable("cursoId") String cursoId) {
        return service.obterCurso(cursoId);
    }


}
