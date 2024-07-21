package com.controlu.backend.service;

import com.controlu.backend.vo.AlunoVO;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    public AlunoVO obterDadosAluno(){
        return new AlunoVO();
    }

    public List<AlunoVO> obterDadosDeTodosAlunoa(){
        return new ArrayList<>();
    }


    public AlunoVO registrarDadosAluno(){
        return new AlunoVO();
    }

    public AlunoVO atualizarDadosAluno(){
        return new AlunoVO();
    }

    public void apagarDadosAlunos(){
    }

}
