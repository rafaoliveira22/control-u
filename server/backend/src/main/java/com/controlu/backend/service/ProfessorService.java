package com.controlu.backend.service;

import com.controlu.backend.controller.ProfessorController;
import com.controlu.backend.entity.model.Professor;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.ProfessorRepository;
import com.controlu.backend.vo.ProfessorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository repository;


    /**
     * MÉTODO PARA OBTER TODOS OS PROFESSORES REGISTRADOS NA BASE DE DADOS
     * ORDERNADO PELO NOME, ORDEM CRESCENTE (A-Z)
     * @return LISTA COM DADOS DE TOOS OS PROFESSORES
     */
    public List<ProfessorVO> obterTodosProfessores(){
        var professores = DozerMapper.parseListObjects(repository.findAllByOrderByProfessorNomeAsc(), ProfessorVO.class);
        professores.stream().forEach(professor -> professor.add(linkTo(methodOn(ProfessorController.class).obterDadosProfessor(professor.getProfessorId())).withSelfRel()));

        return professores;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UM PROFESSOR ESPECÍFICO
     * @param id IDENTIFICAÇÃO DO PROFESSOR
     * @return DADOS DO PROFESSOR
     */
    public ProfessorVO obterDadosProfessor(String id){
        var professor = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(professor, ProfessorVO.class);
        vo.add(linkTo(methodOn(ProfessorController.class).obterDadosProfessor(id)).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO PROFESSOR NA BASE DE DADOS
     * @param professorVO OBJETO CARREGADO COM OS DADOS DO PROFESSOR
     * @return PROFESSOR REGISTRADO
     */
    public ProfessorVO registrarDadosProfessor(ProfessorVO professorVO){
        if (repository.existsById(professorVO.getProfessorId())) {
            throw new IllegalArgumentException("Registro já existe.");
        }

        Professor professor = DozerMapper.parseObject(professorVO, Professor.class);
        var vo = DozerMapper.parseObject(repository.save(professor), ProfessorVO.class);
        vo.add(linkTo(methodOn(ProfessorController.class).obterDadosProfessor(vo.getProfessorId())).withSelfRel());

        return vo;
    }
}
