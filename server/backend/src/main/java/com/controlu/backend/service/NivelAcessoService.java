package com.controlu.backend.service;

import com.controlu.backend.controller.NivelAcessoController;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.NivelAcessoRepository;
import com.controlu.backend.vo.NivelAcessoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class NivelAcessoService {
    @Autowired
    private NivelAcessoRepository repository;

    /**
     * MÉTODO PARA OBTER TODOS OS NÍVEIS DE ACESSO REGISTRADOS NA BASE DE DADOS
     * @return LISTA COM DADOS DE TODOS OS NÍVEIS DE ACESSO
     */
    public List<NivelAcessoVO> obterDadosTodosNiveisDeAcesso(){
        var niveisAcesso = DozerMapper.parseListObjects(repository.findAll(), NivelAcessoVO.class);
        niveisAcesso.stream().forEach(nivelAcesso -> nivelAcesso.add(linkTo(methodOn(NivelAcessoController.class).obterDadosNivelDeAcesso(String.valueOf(nivelAcesso.getNivelAcessoId()))).withSelfRel()));

        return niveisAcesso;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UM NÍVEL DE ACESSO ESPECÍFICO
     * @param id IDENTIFICAÇÃO DO NÍVEL DE ACESSO
     * @return DADOS DO NÍVEL DE ACESSO
     */
    public NivelAcessoVO obterDadosNivelDeAcesso(String id){
        var nivelAcesso = repository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(nivelAcesso, NivelAcessoVO.class);
        vo.add(linkTo(methodOn(NivelAcessoController.class).obterDadosNivelDeAcesso(id)).withSelfRel());

        return vo;
    }
}
