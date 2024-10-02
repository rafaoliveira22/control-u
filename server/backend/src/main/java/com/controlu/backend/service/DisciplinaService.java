package com.controlu.backend.service;

import com.controlu.backend.controller.DisciplinaController;
import com.controlu.backend.entity.model.Disciplina;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.DisciplinaRepository;
import com.controlu.backend.vo.DisciplinaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository repository;


    /**
     * MÉTODO PARA OBTER TODOS AS DISCIPLINAS REGISTRADOS NA BASE DE DADOS
     * ORDERNADO PELO NOME, ORDEM CRESCENTE (A-Z)
     * @return LISTA COM DADOS DE TODOS AS DISCIPLINAS
     */
    public List<DisciplinaVO> obterDadosDeTodasDisciplinas(){
        var disciplinas = DozerMapper.parseListObjects(repository.findAllByOrderByDisciplinaNomeAsc(), DisciplinaVO.class);
        disciplinas.stream().forEach(disciplina -> disciplina.add(linkTo(methodOn(DisciplinaController.class).obterDadosDisciplina(disciplina.getDisciplinaId())).withSelfRel()));

        return disciplinas;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UMA DISCIPLINA ESPECÍFICA
     * @param id IDENTIFICAÇÃO DA DISCIPLINA
     * @return DADOS DA DISCIPLINA BUSCADA
     */
    public DisciplinaVO obterDadosDisciplina(String id){
        var disciplina = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(disciplina, DisciplinaVO.class);
        vo.add(linkTo(methodOn(DisciplinaController.class).obterDadosDisciplina(vo.getDisciplinaId())).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA REGISTRAR UMA NOVA DISCIPLINA NA BASE DE DADOS
     * @param disciplinaVO OBJETO CARREGADO COM OS DADOS DA DISCIPLINA
     * @return DISCIPLINA REGISTRADA
     */
    public DisciplinaVO registrarDadosDiscplina(DisciplinaVO disciplinaVO){
        if (repository.existsById(disciplinaVO.getDisciplinaId())) {
            throw new IllegalArgumentException("Registro já existe.");
        }

        Disciplina disciplina = DozerMapper.parseObject(disciplinaVO, Disciplina.class);
        var vo = DozerMapper.parseObject(repository.save(disciplina), DisciplinaVO.class);
        vo.add(linkTo(methodOn(DisciplinaController.class).obterDadosDisciplina(vo.getDisciplinaId())).withSelfRel());

        return vo;
    }
}
