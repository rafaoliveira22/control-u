package com.controlu.backend.service;

import com.controlu.backend.controller.AlunoController;
import com.controlu.backend.entity.Aluno;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.AlunoRepository;
import com.controlu.backend.vo.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public AlunoVO obterDadosAluno(String id){
        var aluno = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(aluno, AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(id)).withSelfRel());

        return vo;
    }

    public List<AlunoVO> obterDadosDeTodosAlunos(){
        var alunos = DozerMapper.parseListObjects(repository.findAll(), AlunoVO.class);
        alunos.stream().forEach(aluno -> aluno.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(aluno.getId())).withSelfRel()));

        return alunos;
    }


    public AlunoVO registrarDadosAluno(AlunoVO alunoVO){
        if (repository.existsById(alunoVO.getId())) {
            throw new IllegalArgumentException("Registro já existe para esse ID.");
        }

        var aluno = DozerMapper.parseObject(alunoVO, Aluno.class);
        var vo = DozerMapper.parseObject(repository.save(aluno), AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getId())).withSelfRel());


        return vo;
    }

    public AlunoVO atualizarDadosAluno(AlunoVO alunoVO){
        var aluno = repository.findById(alunoVO.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        aluno.setNome((alunoVO.getNome()));

        var vo = DozerMapper.parseObject(repository.save(aluno), AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getId())).withSelfRel());

        return vo;
    }

    public void apagarDadosAlunos(String id){
        var aluno = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        repository.delete(aluno);
    }
}
