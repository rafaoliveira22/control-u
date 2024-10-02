package com.controlu.backend.service;

import com.controlu.backend.controller.CursoController;
import com.controlu.backend.entity.model.Curso;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.CursoRepository;
import com.controlu.backend.vo.CursoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository repository;

    /**
     * MÉTODO PARA OBTER UM CURSO ESPECÍFICO NA BASE DE DADOS
     * @param cursoId ID DO CURSO
     * @return DADOS DO CURSO ENCONTRADO
     */
    public CursoVO obterCurso(String cursoId){
        var curso = repository.findById(Integer.parseInt(cursoId)).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(curso, CursoVO.class);
        vo.add(linkTo(methodOn(CursoController.class).obterCurso(cursoId)).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA OBTER TODOS OS CURSOS REGISTRADOS NA BASE DE DADOS
     * @return LISTA COM OS CURSOS ENCONTRADOS
     */
    public List<CursoVO> obterTodosCursos() {
        var cursos = DozerMapper.parseListObjects(repository.findAll(), CursoVO.class);
        cursos.stream().forEach(curso -> curso.add(linkTo(methodOn(CursoController.class).obterCurso(String.valueOf(curso.getCursoId()))).withSelfRel()));

        return cursos;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO CURSO NA BASE DE DADOS
     * @param cursoVO OBJETO CARREGADO COM OS DADOS DO CURSO
     * @return CURSO REGISTRADO
     */
    public CursoVO registrarDadosCurso(CursoVO cursoVO){
        if (repository.existsByCursoNome(cursoVO.getCursoNome())) {
            throw new IllegalArgumentException("Registro já existe.");
        }

        Curso curso = DozerMapper.parseObject(cursoVO, Curso.class);
        var vo = DozerMapper.parseObject(repository.save(curso), CursoVO.class);
        vo.add(linkTo(methodOn(CursoController.class).obterCurso(String.valueOf(vo.getCursoId()))).withSelfRel());

        return vo;
    }
}
