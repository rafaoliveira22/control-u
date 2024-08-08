package com.controlu.backend.service;

import com.controlu.backend.controller.AlunoController;
import com.controlu.backend.controller.CursoController;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.CursoRepository;
import com.controlu.backend.vo.CursoVO;
import com.controlu.backend.vo.aluno.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    public CursoVO obterCurso(String cursoId){
        var curso = repository.findById(Integer.parseInt(cursoId)).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(curso, CursoVO.class);
        vo.add(linkTo(methodOn(CursoController.class).obterCurso(cursoId)).withSelfRel());

        return vo;
    }
    public List<CursoVO> obterTodosCursos() {
        var cursos = DozerMapper.parseListObjects(repository.findAll(), CursoVO.class);
        cursos.stream().forEach(curso -> curso.add(linkTo(methodOn(CursoController.class).obterCurso(String.valueOf(curso.getCursoId()))).withSelfRel()));

        return cursos;
    }
}
