package com.controlu.backend.controller;

import com.controlu.backend.service.GradeService;
import com.controlu.backend.vo.GradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grade")
public class GradeController {
    @Autowired
    private GradeService service;
    @GetMapping
    public ResponseEntity<?> obterDadosDeTodasGrades(){
        try{
            List<GradeVO> grades = service.obterDadosDeTodasGrades();
            return new ResponseEntity<>(grades, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterDadosGrade(@PathVariable("id") String id){
        try{
            GradeVO grade = service.obterDadosGrade(id);
            return new ResponseEntity<>(grade, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosGrade(@RequestBody GradeVO grade){
        try{
            GradeVO gradeVO = service.registrarDadosGrade(grade);
            return new ResponseEntity<>(gradeVO, HttpStatus.CREATED);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
