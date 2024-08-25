package com.controlu.backend.service;

import com.controlu.backend.controller.SalaController;
import com.controlu.backend.entity.DispositivoLeitura;
import com.controlu.backend.entity.Sala;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.DispositivoLeituraRepository;
import com.controlu.backend.repository.SalaRepository;
import com.controlu.backend.utils.Defines;
import com.controlu.backend.vo.SalaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SalaService {
    @Autowired
    private SalaRepository repository;

    @Autowired
    private DispositivoLeituraRepository dispositivoLeituraRepository;

    /**
     * MÉTODO PARA OBTER A ULTIMA SALA REGISTRADA NA BASE DE DADOS
     * POR MEIO DO PREFIXO (SL OU LAB)
     * @param prefixo SL OU LAB
     * @return ULTIMO SALA REGISTRADA
     */
    private Sala obterUltimaSalaCadastradoPeloPrefixo(String prefixo){
        return repository.findTopBySalaIdStartingWithOrderBySalaIdDesc(prefixo);
    }

    /**
     * MÉTODO PARA OBTER A SEQUÊNCIA NUMERICA DO ID
     * EX: SL001 -> 001
     * @param id
     * @return SEQUÊNCIA NUMERICA EXTRAÍDA DO ID
     */
    private String obterSequenciaNumericaDoId(String id){
        return id.substring(id.length() - 3);
    }

    /**
     * MÉTODO PARA CONSTRUIR O ID DO DISPOSITIVO A SER REGISTRADO
     * 1 - IDENTIFICA-SE O TIPO DA SALA (SALA OU LABORATÓRIO) PARA ESTIPULAR O PREFIXO
     * 2 - OBTEM A ULTIMA SALA REGISTRADA NA BASE DE DADOS, DE ACORDOC C/ O PREFIXO
     * 3 - VERIFICA SE É O 1° REGISTRO, SE FOR O ID SERÁ 001, SE NÃO SOMA-SE 1 NA SEQUÊNCOA ATUAL (5 + 1 -> 6)
     * 4 - FORMA O NOVO ID, FORMATANDO A SEQUÊNCIA PRA TRÊS CARACTERES, COMPLETANDO COM ZEROS À ESQUERDA (5 -> 005)
     * 5 - ADICIONA O PREFIXO E COMPLETA A FORMAÇAÕ DO NOVO ID (SL OU LAB + 005 -> SL005 OU LAB005)
     * @param tipoSala
     * @return
     */
    private String construirIdDaSala(Integer tipoSala){
        Sala ultimaSalaRegistrada;
        String prefixo = "";
        if(tipoSala.equals(Defines.TIPO_SALA_SALA)){
            prefixo = Defines.PREFIXO_ID_TIPO_SALA;
        } else if(tipoSala.equals(Defines.TIPO_SALA_LABORATORIO)){
            prefixo = Defines.PREFIXO_ID_TIPO_SALA_LABORATORIO;
        } else{
            prefixo = "Indefinido";
        }
        ultimaSalaRegistrada = obterUltimaSalaCadastradoPeloPrefixo(prefixo);

        String novoId = "";
        String novaSequenciaNumerica = "";
        if(ultimaSalaRegistrada == null){
            novaSequenciaNumerica = "001";
        } else{
            novaSequenciaNumerica =  String.format("%03d", Integer.parseInt(obterSequenciaNumericaDoId(ultimaSalaRegistrada.getDispositivoId())) + 1);
        }
        novoId = prefixo + novaSequenciaNumerica;
        return novoId;
    }

    /**
     * MÉTODO PARA OBTER TODOS AS SALAS REGISTRADOS NA BASE DE DADOS
     * @return LISTA COM DADOS DE TODOS AS SALAS
     */
    public List<SalaVO> obterTodasSalas(){
        var salas = DozerMapper.parseListObjects(repository.findAll(), SalaVO.class);
        salas.stream().forEach(sala -> sala.add(linkTo(methodOn(SalaController.class).obterSala(sala.getSalaId())).withSelfRel()));

        return salas;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UMA SALA ESPECÍFICA
     * @param id IDENTIFICAÇÃO DA SALA
     * @return DADOS DA SALA
     */
    public SalaVO obterSala(String id){
        var sala = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(sala, SalaVO.class);
        vo.add(linkTo(methodOn(SalaController.class).obterSala(vo.getSalaId())).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA REGISTRAR UMA SALA NA BASE DE DADOS
     * 1 - VERIFICA SE O DISPOSITIVO ESCOLHIDO JÁ NÃO ESTA EM USO POR OUTRA SALA
     * 2 - REGISTRA A SALA
     * 3 - ATUALIZA O STATUS DO DISPOSITIVO PRA 1 (ONLINE)
     *
     * @param salaVO OBJETO CARREGADO COM OS DADOS DA SALA
     * @return SALA REGISTRADA
     */
    public SalaVO registrarDadosSala(SalaVO salaVO){
        if(repository.countByDispositivoId(salaVO.getDispositivoId()) > 0){
            throw new IllegalArgumentException("O DISPOSITIVO escolhido já esta sendo utilizado em outra sala.");
        }

        if(salaVO.getSalaNome().toLowerCase().contains("sala")){
           salaVO.setSalaId(construirIdDaSala(Defines.TIPO_SALA_SALA));
        } else if(salaVO.getSalaNome().toLowerCase().contains("laboratório") || salaVO.getSalaNome().toLowerCase().contains("laboratorio") || salaVO.getSalaNome().toLowerCase().contains("lab")){
            salaVO.setSalaId(construirIdDaSala(Defines.TIPO_SALA_LABORATORIO));
        } else{
            throw new IllegalArgumentException("Não foi possível construir o ID da sala a ser registrada.");
        }

        Sala sala = DozerMapper.parseObject(salaVO, Sala.class);
        var vo = DozerMapper.parseObject(repository.save(sala), SalaVO.class);
        vo.add(linkTo(methodOn(SalaController.class).obterSala(vo.getSalaId())).withSelfRel());

        DispositivoLeitura dispositivoLeitura = new DispositivoLeitura(salaVO.getDispositivoId(), Defines.STATUS_DISPOSITIVO_ONLINE);
        dispositivoLeituraRepository.save(dispositivoLeitura);

        return vo;
    }
}
