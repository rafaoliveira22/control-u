package com.controlu.backend.service;

import com.controlu.backend.controller.AlunoController;
import com.controlu.backend.entity.Aluno;
import com.controlu.backend.entity.embeddable.AlunoId;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.AlunoRepository;
import com.controlu.backend.vo.aluno.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    public AlunoVO obterDadosAluno(String nome, String cursoId, String anoIngressao){
        AlunoId id = new AlunoId(nome, Integer.parseInt(cursoId), Integer.parseInt(anoIngressao));

        var aluno = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse IO."));
        var vo = DozerMapper.parseObject(aluno, AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getId().getAlunoNome(), String.valueOf(vo.getId().getCursoId()), String.valueOf(vo.getId().getAlunoAnoIngressao()))).withSelfRel());

        return vo;
    }

    public AlunoVO obterDadosAluno(String ra){
        var aluno = repository.findByAlunoRa(ra).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse RA."));
        var vo = DozerMapper.parseObject(aluno, AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(ra)).withSelfRel());

        return vo;
    }

    public List<AlunoVO> obterDadosDeTodosAlunos(){
        var alunos = DozerMapper.parseListObjects(repository.findAll(), AlunoVO.class);
        alunos.stream().forEach(aluno -> aluno.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(aluno.getId().getAlunoNome(), String.valueOf(aluno.getId().getCursoId()), String.valueOf(aluno.getId().getAlunoAnoIngressao()))).withSelfRel()));

        return alunos;
    }


    public AlunoVO registrarDadosAluno(AlunoVO alunoVO){
        AlunoId id = new AlunoId(alunoVO.getId().getAlunoNome(), alunoVO.getId().getCursoId(), alunoVO.getId().getAlunoAnoIngressao());

        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Registro já existe.");
        }

        Aluno aluno = DozerMapper.parseObject(alunoVO, Aluno.class);
        var vo = DozerMapper.parseObject(repository.save(aluno), AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getId().getAlunoNome(), String.valueOf(vo.getId().getCursoId()),  String.valueOf(vo.getId().getAlunoAnoIngressao()))).withSelfRel());

        return vo;
    }

    public AlunoVO atualizarDadosAluno(AlunoVO alunoVO){
        AlunoId id = new AlunoId(alunoVO.getId().getAlunoNome(), alunoVO.getId().getCursoId(), alunoVO.getId().getAlunoAnoIngressao());

        var aluno = repository.findByAlunoRa(alunoVO.getAlunoRa()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse RA."));
        aluno.getId().setAlunoNome((alunoVO.getId().getAlunoNome()));

        var vo = DozerMapper.parseObject(repository.save(aluno), AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getId().getAlunoNome(),  String.valueOf(vo.getId().getCursoId()),  String.valueOf(vo.getId().getAlunoAnoIngressao()))).withSelfRel());

        return vo;
    }

    public void apagarDadosAlunos(String ra){
        var aluno = repository.findByAlunoRa(ra).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse RA."));
        repository.delete(aluno);
    }
}
