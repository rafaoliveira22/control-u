package com.controlu.backend.service;

import com.controlu.backend.controller.GradeController;
import com.controlu.backend.entity.model.CartaoLeitura;
import com.controlu.backend.entity.model.Grade;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.CartaoLeituraRepository;
import com.controlu.backend.repository.GradeRepository;
import com.controlu.backend.utils.Defines;
import com.controlu.backend.vo.GradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class GradeService {
    @Autowired
    private GradeRepository repository;

    @Autowired
    private CartaoLeituraRepository cartaoLeituraRepository;


    /**
     * MÉTODO PARA OBTER TODAS AS GRADES REGISTRADOS NA BASE DE DADOS
     * @return LISTA COM DADOS DE TODOS AS GRADES
     */
    public List<GradeVO> obterDadosDeTodasGrades(){
        var grades = DozerMapper.parseListObjects(repository.findAll(), GradeVO.class);
        grades.stream().forEach(grade -> grade.add(linkTo(methodOn(GradeController.class).obterDadosGrade(String.valueOf(grade.getGradeId()))).withSelfRel()));

        return grades;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UMA GRADE ESPECÍFICA
     * @param id IDENTIFICAÇÃO DA GRADE
     * @return DADOS DA GRADE BUSCADA
     */
    public GradeVO obterDadosGrade(String id){
        var grade = repository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResourceNotFoundException("Nenhum reg istro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(grade, GradeVO.class);
        vo.add(linkTo(methodOn(GradeController.class).obterDadosGrade(String.valueOf(vo.getGradeId()))).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA REGISTRAR UMA NOVA GRADE NA BASE DE DADOS
     * @param gradeVO OBJETO CARREGADO COM OS DADOS DA GRADE
     * @return GRADE REGISTRADA
     */
    public GradeVO registrarDadosGrade(GradeVO gradeVO){
        if(repository.countByCartaoId(gradeVO.getCartaoId()) > 0){
            throw new IllegalArgumentException("O Cartão de Leitura escolhido já esta sendo utilizado em outra grade.");
        }

        Grade grade = DozerMapper.parseObject(gradeVO, Grade.class);
        var vo = DozerMapper.parseObject(repository.save(grade), GradeVO.class);

        CartaoLeitura cartaoLeitura = new CartaoLeitura(vo.getCartaoId(), Defines.STATUS_CARTAO_EM_USO);
        cartaoLeituraRepository.save(cartaoLeitura);

        vo.add(linkTo(methodOn(GradeController.class).obterDadosGrade(String.valueOf(vo.getGradeId()))).withSelfRel());
        return vo;
    }
}
