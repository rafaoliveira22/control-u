package com.controlu.backend.service;

import com.controlu.backend.controller.AcessoController;
import com.controlu.backend.entity.Acesso;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.AcessoRepository;
import com.controlu.backend.repository.DispositivoLeituraRepository;
import com.controlu.backend.repository.SalaRepository;
import com.controlu.backend.vo.AcessoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AcessoService {
    @Autowired
    private AcessoRepository repository;

    @Autowired
    private DispositivoLeituraRepository dispositivoLeituraRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private AlunoService alunoService;


    /**
     * MÉTODO PARA OBTER OS DADOS DE UM ACESSO ESPECÍFICO NA BASE DE DADOS
     * @param id IDENTIFICADOR DO ACESSO
     * @return DADOS DO ACESSO ENCONTRADO
     */
    public AcessoVO obterDadosAcesso(String id){
        var acesso = repository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(acesso, AcessoVO.class);
        vo.add(linkTo(methodOn(AcessoController.class).obterDadosAcesso(String.valueOf(vo.getAcessoId()))).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA OBTER TODOS OS ACESSOS REGISTRADOS NA BASE DE DADOS
     * ORDENADOS POR ACESSO MAIS RECENTE
     * @return LISTA COM DADOS DOS ACESSOS
     */
    public List<AcessoVO> obterDadosTodosAcessos(){
        var acessos = DozerMapper.parseListObjects(repository.findAll(), AcessoVO.class);
        acessos.stream().forEach(acesso -> acesso.add(linkTo(methodOn(AcessoController.class).obterDadosAcesso(String.valueOf(acesso.getAcessoId()))).withSelfRel()));

        return acessos;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO ACESSO NA BASE DE DADOS
     * 1. VALIDA SE O DISPOSITIVO DE LEITURA PASSADO, ESTÁ REGISTRADO NA BASE DE DADOS
     * 2. VALIDA SE O DISPOSITIVO DE LEITURA ESTA ASSOCIADO A ALGUMA SALA (ESPAÇO), SENÃO ESTIVER,
     * NÃO TEM COMO ELE TER LIDO UM ACESSO
     * 3. VALIDA SE O ALUNO PASSADO, ESTÁ REGISTRADO NA BASE DE DADOS
     *
     * @param acessoVO OBJETO CARREGADO COM OS DADOS DO ACESSO
     * @return ACESSO REGISTRADO
     */
    public AcessoVO registrarDadosAcesso(AcessoVO acessoVO){
        if(!(dispositivoLeituraRepository.existsById(acessoVO.getDispositivoId()))){
            throw new ResourceNotFoundException("O dispositivo " + acessoVO.getDispositivoId() + " é inválido.");
        }

        if(salaRepository.countByDispositivoId(acessoVO.getDispositivoId()) <= 0){
            throw new IllegalArgumentException("O dispositivo " + acessoVO.getDispositivoId() + " é inválido.");
        }

        if(!(alunoService.verificarSeEstaRegistrado(acessoVO.getAlunoId()))){
            throw new ResourceNotFoundException("O aluno " + acessoVO.getAlunoId() + " não tem autorização para realizar um acesso.");
        }
        acessoVO.setAcessoEntrada(LocalDateTime.now());

        Acesso acesso = DozerMapper.parseObject(acessoVO, Acesso.class);
        var vo = DozerMapper.parseObject(repository.save(acesso), AcessoVO.class);
        vo.add(linkTo(methodOn(AcessoController.class).obterDadosAcesso(String.valueOf(vo.getAcessoId()))).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA ATUALIZAR UM REGISTRO DE ACESSO
     * @param acessoVO OBJETO CARREGADO COM OS NOVOS DADOS DO ACESSO
     * @return ACESSO ATUALIZADO
     */
    public AcessoVO atualizarDadosAcesso(AcessoVO acessoVO){
        var acesso = repository.findById(acessoVO.getAcessoId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));

        if(!(acesso.getAcessoEntrada().isEqual(acessoVO.getAcessoEntrada())) || !(acesso.getAlunoId().equals(acessoVO.getAlunoId())) || (acesso.getDispositivoId().equals(acessoVO.getDispositivoId()))){
            throw new IllegalArgumentException("No acesso, só é possível atualizar o horário de saída.");
        }

        LocalDateTime acessoSaida = LocalDateTime.now();
        if(acessoSaida.isAfter(acesso.getAcessoEntrada())){
            throw new IllegalArgumentException("O horario de saída " + acessoSaida + " não pode ser maior que o horário de entrada");
        }

        if(acessoSaida.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("O horario de saída " + acessoSaida + " não pode ser maior que o horário atual");
        }

        acesso.setAcessoSaida(acessoSaida);

        var vo = DozerMapper.parseObject(repository.save(acesso), AcessoVO.class);
        vo.add(linkTo(methodOn(AcessoController.class).obterDadosAcesso(String.valueOf(vo.getAcessoId()))).withSelfRel());

        return vo;
    }
}
