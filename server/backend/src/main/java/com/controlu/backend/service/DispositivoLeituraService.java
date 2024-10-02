package com.controlu.backend.service;

import com.controlu.backend.controller.DispositivoLeituraController;
import com.controlu.backend.entity.model.DispositivoLeitura;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.DispositivoLeituraRepository;
import com.controlu.backend.utils.Defines;
import com.controlu.backend.vo.DispositivoLeituraVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class DispositivoLeituraService {

    @Autowired
    private DispositivoLeituraRepository repository;

    /**
     * MÉTODO PARA OBTER O ULTIMO DISPOSITIVO DE LEITURA REGISTRADO NA BASE DE DADOS
     * POR MEIO DO PREFIXO (CAT OU TL)
     * @param prefixo CAT OU TL
     * @return ULTIMO DISPOSITIVO DE LEITURA REGISTRADO
     */
    private DispositivoLeitura obterUltimoDispositivoCadastradoPeloPrefixo(String prefixo){
        return repository.findTopByDispositivoIdStartingWithOrderByDispositivoIdDesc(prefixo);
    }

    /**
     * MÉTODO PARA OBTER A SEQUÊNCIA NUMERICA DO ID
     * EX: CAT001 -> 001
     * @param id
     * @return SEQUÊNCIA NUMERICA EXTRAÍDA DO ID
     */
    private String obterSequenciaNumericaDoId(String id){
        return id.substring(id.length() - 3);
    }

    /**
     * MÉTODO PARA CONSTRUIR O ID DO DISPOSITIVO A SER REGISTRADO
     * 1 - IDENTIFICA-SE O TIPO DO DISPOSITIVO (CATRACA - 1 OU TOTEM - 2) PARA ESTIPULAR O PREFIXO
     * 2 - OBTEM O ULTIMO DISPOSITIVO REGISTRADO NA BASE DE DADOS, DE ACORDOC C/ O PREFIXO
     * 3 - VERIFICA SE É O 1° REGISTRO, SE FOR O ID SERÁ 001, SE NÃO SOMA-SE 1 NA SEQUÊNCOA ATUAL (5 + 1 -> 6)
     * 4 - FORMA O NOVO ID, FORMATANDO A SEQUÊNCIA PRA TRÊS CARACTERES, COMPLETANDO COM ZEROS À ESQUERDA (5 -> 005)
     * 5 - ADICIONA O PREFIXO E COMPLETA A FORMAÇAÕ DO NOVO ID (CAT OU TL + 005 -> CAT005 OU TL005)
     * @param tipoDispositivo
     * @return
     */
    private String construirIdDoDispositivo(Integer tipoDispositivo){
        DispositivoLeitura ultimoDispositivoRegistrado;
        String prefixo = "";
        if(tipoDispositivo.equals(Defines.TIPO_DISPOSITIVO_CATRACA)){
            prefixo = Defines.PREFIXO_ID_TIPO_DISPOSITIVO_CATRACA;
        } else if(tipoDispositivo.equals(Defines.TIPO_DISPOSITIVO_TOTEM)){
            prefixo = Defines.PREFIXO_ID_TIPO_DISPOSITIVO_TOTEM;
        } else{
            prefixo = "Indefinido";
        }
        ultimoDispositivoRegistrado = obterUltimoDispositivoCadastradoPeloPrefixo(prefixo);

        String novoId = "";
        String novaSequenciaNumerica = "";
        if(ultimoDispositivoRegistrado == null){
            novaSequenciaNumerica = "001";
        } else{
            novaSequenciaNumerica =  String.format("%03d", Integer.parseInt(obterSequenciaNumericaDoId(ultimoDispositivoRegistrado.getDispositivoId())) + 1);
        }
        novoId = prefixo + novaSequenciaNumerica;
        return novoId;
    }

    /**
     * MÉTODO PARA OBTER TODOS OS DISPOSITIVOS DE LEITURA REGISTRADOS NA BASE DE DADOS
     * BUSCANDO PELO STATUS
     * @return LISTA COM DADOS DE TODOS OS DISPOSITIVOS
     */
    public List<DispositivoLeituraVO> obterDadosDeTodosDispositivosPorStatus(String status){
        var dispositivos = DozerMapper.parseListObjects(repository.findByDispositivoStatus(Integer.parseInt(status)), DispositivoLeituraVO.class);
        dispositivos.stream().forEach(dispositivo -> {
            dispositivo.add(linkTo(methodOn(DispositivoLeituraController.class).obterDadosDispositivo(dispositivo.getDispositivoId())).withSelfRel());
            dispositivo.setDispositivoTipo(obterTipoDispositivo(dispositivo.getDispositivoId()));
        });

        return dispositivos;
    }

    /**
     * MÉTODO PARA OBTER TODOS OS DISPOSITIVOS DE LEITURA REGISTRADOS NA BASE DE DADOS
     * @return LISTA COM DADOS DE TODOS OS DISPOSITIVOS
     */
    public List<DispositivoLeituraVO> obterDadosDeTodosDispositivos(){
        var dispositivos = DozerMapper.parseListObjects(repository.findAll(), DispositivoLeituraVO.class);
        dispositivos.stream().forEach(dispositivo -> {
                dispositivo.add(linkTo(methodOn(DispositivoLeituraController.class).obterDadosDispositivo(dispositivo.getDispositivoId())).withSelfRel());
                dispositivo.setDispositivoTipo(obterTipoDispositivo(dispositivo.getDispositivoId()));
        });

        return dispositivos;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UM DISPOSITIVO DE LEITURA ESPECÍFICO
     * @param id IDENTIFICAÇÃO DO DISPOSITIVO DE LEITURA
     * @return DADOS DO DISPOSITIVO BUSCADO
     */
    public DispositivoLeituraVO obterDadosDispositivo(String id){
        var dispositivo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(dispositivo, DispositivoLeituraVO.class);

        vo.setDispositivoTipo(obterTipoDispositivo(vo.getDispositivoId()));
        vo.add(linkTo(methodOn(DispositivoLeituraController.class).obterDadosDispositivo(vo.getDispositivoId())).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO DISPOSITIVO DE LEITURA NA BASE DE DADOS
     * @param dispositivoLeituraVO OBJETO CARREGADO COM OS DADOS DO DISPOSITIVO
     * @return DISPOSITIVO REGISTRADO
     */
    public DispositivoLeituraVO registrarDadosDispositivo(DispositivoLeituraVO dispositivoLeituraVO){
        dispositivoLeituraVO.setDispositivoId(construirIdDoDispositivo(dispositivoLeituraVO.getDispositivoTipo()));
        dispositivoLeituraVO.setDispositivoStatus(Defines.STATUS_DISPOSITIVO_OFFLINE);

        DispositivoLeitura dispositivo = DozerMapper.parseObject(dispositivoLeituraVO, DispositivoLeitura.class);
        var vo = DozerMapper.parseObject(repository.save(dispositivo), DispositivoLeituraVO.class);
        vo.add(linkTo(methodOn(DispositivoLeituraController.class).obterDadosDispositivo(vo.getDispositivoId())).withSelfRel());

        return vo;
    }

    private Integer obterTipoDispositivo(String id){
        if(id.contains("CAT")){
            return Defines.TIPO_DISPOSITIVO_CATRACA;
        } else if(id.contains("TL")){
            return Defines.TIPO_DISPOSITIVO_TOTEM;
        } else{
           return 0;
        }
    }
}
