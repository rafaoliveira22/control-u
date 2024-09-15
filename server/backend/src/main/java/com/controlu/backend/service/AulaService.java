package com.controlu.backend.service;

import com.controlu.backend.controller.AulaController;
import com.controlu.backend.entity.Aula;
import com.controlu.backend.entity.Grade;
import com.controlu.backend.entity.Sala;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.AulaRepository;
import com.controlu.backend.repository.GradeRepository;
import com.controlu.backend.repository.SalaRepository;
import com.controlu.backend.utils.DateUtils;
import com.controlu.backend.vo.AulaLeituraVO;
import com.controlu.backend.vo.AulaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AulaService {
    @Autowired
    private AulaRepository repository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private DateUtils dateUtils;

    /**
     * MÉTODO PARA OBTER OS DADOS DE UMA AULA ESPECÍFICA NA BASE DE DADOS
     * @param id IDENTIFICADOR DA AULA
     * @return DADOS DA AULA ENCONTRADA
     */
    public AulaVO obterDadosAula(String id){
        var aula = repository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(aula, AulaVO.class);
        vo.add(linkTo(methodOn(AulaController.class).obterDadosAula(String.valueOf(vo.getAulaId()))).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA OBTER TODOS AS AULAS REGISTRADAS NA BASE DE DADOS
     * ORDENADOS POR AULA ABERTA MAIS RECENTE
     * @return LISTA COM DADOS DAS AULAS
     */
    public List<AulaVO> obterDadosTodasAulas(){
        var aulas = DozerMapper.parseListObjects(repository.findAllByOrderByAulaAberturaDesc(), AulaVO.class);
        aulas.stream().forEach(aula -> aula.add(linkTo(methodOn(AulaController.class).obterDadosAula(String.valueOf(aula.getAulaId()))).withSelfRel()));

        return aulas;
    }


    /**
     * MÉTODO PARA REALIZAR UM NOVO REGISTRO DE AULA NA BASE DE DADOS
     *
     * 1. PARA IDENTIFICAR A SALA DA AULA, É NECESSÁRIO PEGAR O ID DO DISPOSITIVO QUE FEZ A LEITURA DO CARTÃO DA GRADE DA AULA
     * E POR MEIO DESSE ID, BUSCAR A SALA, JÁ QUE O DISPOSITIVO ESTÁ ASSOCIADO A AULA.
     *
     * 2. VALIDAR AULA JÁ ABERTA
     *    - Pra uma aula estar aberta, precisa ter em aula_abertura a data de hoje, aula_fechamento ser null.
     *    - Caso ja tenha uma aula aberta com a grade informada, verificar se a sala da nova requisição de abertura é diferente
     *      da aberta, se for igual, considerar fechamento de aula, se for diferente, considerar uma "segunda parte da aula" em
     *      um espaço diferente, ou seja, um novo registro de aula, mas, não pode abrir aula em outro espaço com uma aula aberta em outra sala,
     *      que não foi fechada.
     *
     * @param aulaLeituraVO OBJETO CARREGADO COM OS DADOS NCESSÁRIOS PARA ABERTURA DA AULA
     * @return AULA REGISTRADA
     */
    public AulaVO registrarDadosAula(AulaLeituraVO aulaLeituraVO){
        AulaVO aulaVO = DozerMapper.parseObject(aulaLeituraVO, AulaVO.class);

        Optional<Sala> salaAula = salaRepository.findByDispositivoId(aulaVO.getDispositivoId());
        if(salaAula.isPresent()){
            aulaVO.setSalaId(salaAula.get().getSalaId());
        } else {
            throw new IllegalArgumentException("O dispositivo de leitura " + aulaVO.getDispositivoId()+ " não está associado há uma sala.");
        }


        Optional<Grade> grade = gradeRepository.findByCartaoId(aulaVO.getCartaoId());
        if(grade.isPresent()){
            aulaVO.setGradeId(grade.get().getGradeId());
        } else{
            throw new IllegalArgumentException("O cartão lido, " + aulaVO.getCartaoId()+ ", não está associado há uma grade de aula.");
        }

        Optional<Aula> aulaValidacao = repository.findAulaByGradeIdAndAulaAberturaTodayAndAulaFechamentoNull(aulaVO.getGradeId());
        if(aulaValidacao.isPresent()){
            if(aulaValidacao.get().getSalaId().equals(aulaVO.getSalaId())){
                aulaVO.setAulaId(aulaValidacao.get().getAulaId());
                aulaVO.setAulaAbertura(aulaValidacao.get().getAulaAbertura());
                aulaVO.setAulaFechamento(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos());
            } else{
                throw new IllegalArgumentException("A aula " + grade.get().getDisciplinaId() + " não pode ser aberta em uma nova sala pois essa aula ainda está aberta na sala " + aulaValidacao.get().getSalaId());
            }
        } else{
            aulaVO.setAulaAbertura(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos());
            aulaVO.setAulaFechamento(null);
        }

        Aula aula = DozerMapper.parseObject(aulaVO, Aula.class);
        var vo = DozerMapper.parseObject(repository.save(aula), AulaVO.class);
        vo.add(linkTo(methodOn(AulaController.class).obterDadosAula(String.valueOf(vo.getAulaId()))).withSelfRel());

        return vo;
    }
}
