package com.controlu.backend.service;

import com.controlu.backend.controller.CartaoController;
import com.controlu.backend.entity.model.CartaoLeitura;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.CartaoLeituraRepository;
import com.controlu.backend.utils.Defines;
import com.controlu.backend.vo.CartaoLeituraVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CartaoLeituraService {
    @Autowired
    private CartaoLeituraRepository repository;
    /**
     * MÉTODO PARA OBTER O ULTIMO CARTÃO DE LEITURA REGISTRADO NA BASE DE DADOS
     * @return ULTIMO CARTÃO DE LEITURA REGISTRADO
     */
    private CartaoLeitura obterUltimoCartaoRegistrado(){
        return repository.findTopByOrderByCartaoIdDesc();
    }

    /**
     * MÉTODO PARA OBTER A SEQUÊNCIA NUMERICA DO ID
     * EX: C001 -> 001
     * @param id
     * @return SEQUÊNCIA NUMERICA EXTRAÍDA DO ID
     */
    private String obterSequenciaNumericaDoId(String id){
        return id.substring(id.length() - 3);
    }

    /**
     * MÉTODO PARA CONSTRUIR O ID DO CARTÃO A SER REGISTRADO
     * @return NOVO ID
     */
    private String construirIdDoDispositivo(){
        CartaoLeitura ultimoCartaoRegistrado = obterUltimoCartaoRegistrado();


        String novoId = "";
        String novaSequenciaNumerica = "";

        if(ultimoCartaoRegistrado == null){
            novaSequenciaNumerica = "001";
        } else{
            novaSequenciaNumerica =  String.format("%03d", Integer.parseInt(obterSequenciaNumericaDoId(ultimoCartaoRegistrado.getCartaoId())) + 1);
        }
        novoId = "C" + novaSequenciaNumerica;
        return novoId;
    }

    /**
     * MÉTODO PARA OBTER TODOS OS CARTÕES DE LEITURA REGISTRADOS NA BASE DE DADOS
     * @return LISTA COM DADOS DE TODOS OS CARTÕES
     */
    public List<CartaoLeituraVO> obterDadosTodosCartoes(){
        var cartoes = DozerMapper.parseListObjects(repository.findAll(), CartaoLeituraVO.class);
        cartoes.stream().forEach(cartao -> cartao.add(linkTo(methodOn(CartaoController.class).obterDadosCartao(cartao.getCartaoId())).withSelfRel()));

        return cartoes;
    }

    /**
     * MÉTODO PARA OBTER TODOS OS CARTÕES DE LEITURA REGISTRADOS NA BASE DE DADOS
     * QUE NÃO ESTÃO ASSOCIADOS HÁ UMA GRADE DE AULA
     * @return LISTA COM DADOS DE TODOS OS CARTÕES
     */
    public List<CartaoLeituraVO> obterDadosTodosCartoesQueNaoEstaoAssociadosAUmaGrade(){
        var cartoes = DozerMapper.parseListObjects(repository.findCartoesNotInGrade(), CartaoLeituraVO.class);
        cartoes.stream().forEach(cartao -> cartao.add(linkTo(methodOn(CartaoController.class).obterDadosCartao(cartao.getCartaoId())).withSelfRel()));

        return cartoes;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UM CARTÃO DE LEITURA ESPECÍFICO
     * @param id IDENTIFICAÇÃO DO CARTÃO DE LEITURA
     * @return DADOS DO CARTÃO BUSCADO
     */
    public CartaoLeituraVO obterDadosCartao(String id){
        var cartao = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(cartao, CartaoLeituraVO.class);
        vo.add(linkTo(methodOn(CartaoController.class).obterDadosCartao(vo.getCartaoId())).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO CARTÃO DE LEITURA NA BASE DE DADOS
     * @return CARTÃO REGISTRADO
     */
    public CartaoLeituraVO registrarDadosCartao(){
        CartaoLeituraVO cartaoLeituraVO = new CartaoLeituraVO(construirIdDoDispositivo(), Defines.STATUS_CARTAO_SEM_USO);

        CartaoLeitura cartao = DozerMapper.parseObject(cartaoLeituraVO, CartaoLeitura.class);
        var vo = DozerMapper.parseObject(repository.save(cartao), CartaoLeituraVO.class);
        vo.add(linkTo(methodOn(CartaoController.class).obterDadosCartao(vo.getCartaoId())).withSelfRel());

        return vo;
    }
}
