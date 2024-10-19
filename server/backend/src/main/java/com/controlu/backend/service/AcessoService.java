package com.controlu.backend.service;

import com.controlu.backend.controller.AcessoController;
import com.controlu.backend.entity.model.Acesso;
import com.controlu.backend.entity.model.Aluno;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.AcessoRepository;
import com.controlu.backend.repository.AlunoRepository;
import com.controlu.backend.repository.DispositivoLeituraRepository;
import com.controlu.backend.repository.SalaRepository;
import com.controlu.backend.utils.DateUtils;
import com.controlu.backend.vo.AcessoLeituraVO;
import com.controlu.backend.vo.AcessoVO;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private DateUtils dateUtils;

    @Autowired
    private ReconhecimentoFacialService reconhecimentoFacialService;


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
        var acessos = DozerMapper.parseListObjects(repository.findAllByOrderByAcessoEntradaDesc(), AcessoVO.class);
        acessos.stream().forEach(acesso -> acesso.add(linkTo(methodOn(AcessoController.class).obterDadosAcesso(String.valueOf(acesso.getAcessoId()))).withSelfRel()));

        return acessos;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO ACESSO NA BASE DE DADOS
     *
     * 1. VALIDA SE HÁ UM ACESSO EM ABERTO
     *    - Para um aluno ter um acesso em aberto, o aluno precisa ter em acesso_entrada a data de hoje,
     *    caso tenha o registro, fazer atualização do horario de saída, senão, registrar um novo acesso
     *    - Um registro de acesso só registrar o acesso entrada 1x, e saída 1x, pra cada reentrada da faculdade,
     *    um registro de acesso e criado.
     *
     * 2. VALIDA SE O DISPOSITIVO DE LEITURA PASSADO, ESTÁ REGISTRADO NA BASE DE DADOS
     * 3. VALIDA SE O DISPOSITIVO DE LEITURA ESTA ASSOCIADO A ALGUMA SALA (ESPAÇO), SENÃO ESTIVER,
     * NÃO TEM COMO ELE TER LIDO UM ACESSO
     * 4. VALIDA SE O ALUNO PASSADO, ESTÁ REGISTRADO NA BASE DE DADOS
     *
     * @param acessoLeituraVO OBJETO CARREGADO COM OS DADOS DO ACESSO
     * @return ACESSO REGISTRADO
     */
    public AcessoVO registrarDadosAcesso(AcessoLeituraVO acessoLeituraVO) throws IOException {
        AcessoVO acessoVO = DozerMapper.parseObject(acessoLeituraVO, AcessoVO.class);
        acessoVO.setAcessoFaceMomentoEntrada(Base64.getDecoder().decode(acessoLeituraVO.getFaceEntrada()));

        if(!(dispositivoLeituraRepository.existsById(acessoVO.getDispositivoId()))){
            throw new ResourceNotFoundException("O dispositivo " + acessoVO.getDispositivoId() + " é inválido.");
        }

        if(salaRepository.countByDispositivoId(acessoVO.getDispositivoId()) <= 0){
            throw new IllegalArgumentException("O dispositivo " + acessoVO.getDispositivoId() + " é inválido.");
        }

        if(!(alunoService.verificarSeEstaRegistrado(acessoVO.getAlunoId()))){
            throw new ResourceNotFoundException("O aluno " + acessoVO.getAlunoId() + " não tem autorização para realizar um acesso.");
        }

        Optional<Acesso> acessoValidacao = repository.findAcessoByAlunoIdAndAcessoEntradaTodayAndAcessoSaidaNull(acessoVO.getAlunoId());
        if(acessoValidacao.isPresent()){
            // ACESSO EM ABERTO (SAÍDA)
            acessoVO.setAcessoId(acessoValidacao.get().getAcessoId());
            acessoVO.setAcessoEntrada(acessoValidacao.get().getAcessoEntrada());
            acessoVO.setAcessoSaida(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos());
        } else{
            // SEM ACESSO EM ABERTO (ENTRADA)
            Optional<Aluno> aluno = alunoRepository.findById(acessoVO.getAlunoId());
            if(aluno.isPresent()){
                System.out.println(reconhecimentoFacialService.enviarImagensParaReconhecimento(acessoVO.getAcessoFaceMomentoEntrada(), aluno.get().getAlunoFace()));
                boolean faceReconhecida = true;
                if(!faceReconhecida){
                    throw new IllegalArgumentException("Reconhecimento facial do aluno " + acessoLeituraVO.getAlunoId() + " inválido! Tente novamente ou contate o suporte.");
                }
            } else{
                throw new IllegalArgumentException("Reconhecimento facial do aluno " + acessoLeituraVO.getAlunoId() + " inválido! Tente novamente ou contate o suporte.");
            }

            acessoVO.setAcessoEntrada(dateUtils.obterDataHoraAtualSemPrecisaoDeSegundos());
            acessoVO.setAcessoSaida(null);
        }

        Acesso acesso = DozerMapper.parseObject(acessoVO, Acesso.class);
        var vo = DozerMapper.parseObject(repository.save(acesso), AcessoVO.class);
        vo.add(linkTo(methodOn(AcessoController.class).obterDadosAcesso(String.valueOf(vo.getAcessoId()))).withSelfRel());

        return vo;
    }


    /**
     * MÉTODO PARA VERIFICAR SE O ALUNO TEM UM ACESSO EM ABERTO
     * @param ra REGISTRO DO ALUNO
     * @return TRUE -> TEM ACESSO EM ABERTO, FALSE -> NÃO TEM ACESSO EM ABERTO
     */
    public boolean verificarSeTemAcessoEmAberto(String ra){
        Optional<Acesso> acessoValidacao = repository.findAcessoByAlunoIdAndAcessoEntradaTodayAndAcessoSaidaNull(ra);
        return acessoValidacao.isPresent();
    }
}
