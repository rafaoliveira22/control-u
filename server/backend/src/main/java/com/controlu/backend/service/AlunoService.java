package com.controlu.backend.service;

import com.controlu.backend.controller.AlunoController;
import com.controlu.backend.entity.model.Aluno;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.AlunoRepository;
import com.controlu.backend.vo.AlunoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


import java.util.List;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository repository;

    /**
     * MÉTODO PARA OBTER OS DADOS DE UM ALUNO ESPECÍFICO NA BASE DE DADOS
     * @param ra REGISTRO DO ALUNO
     * @return DADOS DO ALUNO ENCONTRADO
     */
    public AlunoVO obterDadosAluno(String ra){
        var aluno = repository.findById(ra).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(aluno, AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getAlunoRa())).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA OBTER DADOS DE TODOS OS ALUNOS, ORDERNADO POR NOME (ASC)
     * @return LISTA COM DADOS DE TOOS OS ALUNOS
     */

    public List<AlunoVO> obterDadosDeTodosAlunos(){
        var alunos = DozerMapper.parseListObjects(repository.findAllByOrderByAlunoNomeAsc(), AlunoVO.class);
        alunos.stream().forEach(aluno -> aluno.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(aluno.getAlunoRa())).withSelfRel()));

        return alunos;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO ALUNO NA BASE DE DADOS
     * @param alunoVO OBJETO CARREGADO COM OS DADOS DO ALUNO
     * @return ALUNO REGISTRADO
     */
    public AlunoVO registrarDadosAluno(AlunoVO alunoVO){
        if (repository.existsById(alunoVO.getAlunoRa())) {
            throw new IllegalArgumentException("Registro já existe.");
        }

        Aluno aluno = DozerMapper.parseObject(alunoVO, Aluno.class);
        var vo = DozerMapper.parseObject(repository.save(aluno), AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getAlunoRa())).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA ATUALIZAR O REGISTRO DO ALUNO NA BASE DE DADOS
     * @param alunoVO OBJETO CARREGADO COM OS DADOS DO ALUNO
     * @return ALUNO ATUALIZADO
     */
    public AlunoVO atualizarDadosAluno(AlunoVO alunoVO){
        var aluno = repository.findById(alunoVO.getAlunoRa()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse RA."));
        aluno.setAlunoNome(alunoVO.getAlunoNome());
        aluno.setCursoId(alunoVO.getCursoId());

        var vo = DozerMapper.parseObject(repository.save(aluno), AlunoVO.class);
        vo.add(linkTo(methodOn(AlunoController.class).obterDadosAluno(vo.getAlunoRa())).withSelfRel());

        return vo;
    }

    /**
     * MÉTODDO PARA APAGAR O REGISTRO DO ALUNO NA BASE DE DADOS
     * @param ra REGISTRO DO ALUNO
     */
    public void apagarDadosAlunos(String ra){
        var aluno = repository.findById(ra).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse RA."));
        repository.delete(aluno);
    }

    /**
     * MÉTODO PARA VERIFICAR SE O RA INDICADO ESTA REGISTRADO NA BASE DE DADOS
     * @param ra REGISTRO DO ALUNO
     * @return TRUE -> ESTA REGISTRADO, FALSE -> NÃO ESTA REGISTRADO
     */
    public boolean verificarSeEstaRegistrado(String ra){
        return repository.existsById(ra);
    }
}
