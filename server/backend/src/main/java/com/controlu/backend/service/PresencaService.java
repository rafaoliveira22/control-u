package com.controlu.backend.service;

import com.controlu.backend.controller.AulaController;
import com.controlu.backend.controller.PresencaController;
import com.controlu.backend.entity.Aula;
import com.controlu.backend.entity.Presenca;
import com.controlu.backend.entity.Sala;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.AcessoRepository;
import com.controlu.backend.repository.AulaRepository;
import com.controlu.backend.repository.PresencaRepository;
import com.controlu.backend.repository.SalaRepository;
import com.controlu.backend.utils.DateUtils;
import com.controlu.backend.vo.AulaVO;
import com.controlu.backend.vo.PresencaLeituraVO;
import com.controlu.backend.vo.PresencaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository repository;

    @Autowired
    private SalaRepository salaRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private DateUtils dateUtils;

    /**
     * MÉTODO PARA OBTER OS DADOS DE UMA PRESENÇA ESPECÍFICA NA BASE DE DADOS
     * @param id IDENTIFICADOR DA PRESENÇA
     * @return DADOS DA PRESENÇA ENCONTRADA
     */
    public PresencaVO obterDadosPresenca(String id){
        var aula = repository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(aula, PresencaVO.class);
        vo.add(linkTo(methodOn(PresencaController.class).obterDadosPresenca(String.valueOf(vo.getPresencaId()))).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA OBTER TODOS AS PRESENÇAS REGISTRADAS NA BASE DE DADOS
     * ORDENADOS POR PRESENÇA MAIS RECENTE
     * @return LISTA COM DADOS DAS PRESENÇAS
     */
    public List<PresencaVO> obterDadosTodasPresencas(){
        var presencas = DozerMapper.parseListObjects(repository.findAllByOrderByPresencaEntradaDesc(), PresencaVO.class);
        presencas.stream().forEach(presenca -> presenca.add(linkTo(methodOn(PresencaController.class).obterDadosPresenca(String.valueOf(presenca.getPresencaId()))).withSelfRel()));

        return presencas;
    }

    /**
     * MÉTODO PARA REALIZAR UM NOVO REGISTRO DE PRESENÇA NA BASE DE DADOS
     * 1. VALIDAR SE O ALUNO TEM AUTORIZAÇÃO PRA ACESSAR A AULA (OU SEJA, SE ESTA NA BASE)
     * 2. VALIDDAR SE O ALUNO ACESSOU A FACULDADE, SENÃO, NÃO TEM COMO O ALUNO REGISTRAR PRESENÇA NA AULA,
     *    SE O MESMO NÃO ENTROU NA FACULDADE NO DIA
     * 2. VALIDAR SE A AULA ESTA ABERTA
     *    - inicialmente, as informações que temos é o R.A do aluno, por ele ter lido no totem da sala
     *    e o dispositivo que fez a leitura
     *    - buscar na tabela sala, qual a sala é a que esta o dispositivo que fez a leitura
     *      e assim, buscar a aula, por meio da sala_id e com os dados fazer 2 operações
     *      1. verificar se a aula esta aberta (aula_abertura com a data de hoje e aula_fechamento null)
     *      2. se sim, obter aula_id, senão retornar um aviso.
     *
     * 3. VALIDAR SE O ALUNO JA REGISTROU PRESENÇA, SE SIM, ATUALIZAR O HORARIO DE SAIDA
     * @param presencaLeituraVO OBJETO CARREGADO COM OS DADOS DA PRESENCA
     * @return PRESENCA REGISTRADA
     */
    public PresencaVO registrarDadosPresenca(PresencaLeituraVO presencaLeituraVO){
        PresencaVO presencaVO = DozerMapper.parseObject(presencaLeituraVO, PresencaVO.class);

        if(!(alunoService.verificarSeEstaRegistrado(presencaVO.getAlunoId()))){
            throw new IllegalArgumentException("O aluno " + presencaVO.getAlunoId() + " não tem autorização para realizar um acesso à essa aula.");
        }

        if(acessoRepository.findAcessoByAlunoIdAndAcessoEntradaToday(presencaVO.getAlunoId()).isPresent()){
            // BUSCA DA SALA QUE ESTA O DISPOSITIVO QUE FEZ A LEITURA DA CARTEIRINHA
            Optional<Sala> salaAula = salaRepository.findByDispositivoId(presencaVO.getDispositivoId());
            if(salaAula.isPresent()){
                // BUSCA A AULA EM ABERTO
                Optional<Aula> aulaAbertaValidacao = aulaRepository.findAulaBySalaIdAndAulaAberturaTodayAndAulaFechamentoNull(salaAula.get().getSalaId());
                if(aulaAbertaValidacao.isPresent()){
                    // SE AULA ESTIVER EM ABERTO, PERMITE REGISTRAR PRESENÇA DO ALUNO
                    presencaVO.setAulaId(aulaAbertaValidacao.get().getAulaId());

                    // BUSCA A PRESENÇA EM ABERTO DO ALUNO, POR MEIO DO ID DA AULA E O R.A DO ALUNO
                    Optional<Presenca> presencaAtual = repository.findByAulaIdAnAlunoIdAndPresencaSaidaNull(presencaVO.getAulaId(), presencaVO.getAlunoId());
                    if(presencaAtual.isPresent()){
                        // SE ESTIVER PRESENÇA EM ABERTO, ATUALIZAR HORÁRIO DE SAÍDA
                        presencaVO.setPresencaId(presencaAtual.get().getPresencaId());
                        presencaVO.setPresencaEntrada(presencaAtual.get().getPresencaEntrada());
                        presencaVO.setPresencaSaida(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos());
                    } else{
                        // SENÃO, O ALUNO ACABOU DE CHEGAR NA AULA (INDEPENDENTE DA VEZ), ELE PODE REGISTRAR ENTRADA
                        // QUANTAS VEZES QUISER, ENTÃO, REGISTRAR O HORÁRIO DE ENTRADA
                        presencaVO.setPresencaEntrada(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos());
                        presencaVO.setPresencaSaida(null);
                    }
                } else{
                    throw new IllegalArgumentException("A aula está fechada! Não é possível mais registrar presença.");
                }
            } else {
                throw new IllegalArgumentException("O dispositivo de leitura " + presencaVO.getDispositivoId()+ " não está associado há uma sala.");
            }
        } else{
            throw new IllegalArgumentException("O aluno " + presencaVO.getAlunoId() + " não acessou a faculdade hoje, sendo assim, não pode registrar presença na aula.");
        }


        Presenca presenca = DozerMapper.parseObject(presencaVO, Presenca.class);
        var vo = DozerMapper.parseObject(repository.save(presenca), PresencaVO.class);
        vo.add(linkTo(methodOn(PresencaController.class).obterDadosPresenca(String.valueOf(vo.getPresencaId()))).withSelfRel());

        return vo;
    }
}
