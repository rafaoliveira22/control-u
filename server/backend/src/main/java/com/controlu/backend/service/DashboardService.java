package com.controlu.backend.service;

import com.controlu.backend.repository.*;
import com.controlu.backend.utils.Defines;
import com.controlu.backend.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private RegistrosRecentesRepository registrosRecentesRepository;


    public DashboardAlunosAulaVO obterDadosAlunosAula(){
        DashboardAlunosAulaVO dashboardAlunosAulaVO = new DashboardAlunosAulaVO();

        dashboardAlunosAulaVO.setQuantidadeAlunosEmAula(presencaRepository.countAlunosEmAula());
        dashboardAlunosAulaVO.setQuantidadeAlunosForaDeAula(acessoRepository.countAcessosDataAtualAndAcessoSaidaNull() - dashboardAlunosAulaVO.getQuantidadeAlunosEmAula());

        return dashboardAlunosAulaVO;
    }

    public DashboardAlunosAcessoVO obterDadosAlunosAcesso(){
        DashboardAlunosAcessoVO dashboardAlunosAcessoVO = new DashboardAlunosAcessoVO();
        dashboardAlunosAcessoVO.setQuantidadeAlunosNaFaculdade(acessoRepository.countAcessosDataAtualAndAcessoSaidaNull());
        dashboardAlunosAcessoVO.setQuantidadeAlunosForaDaFaculdade((int) (alunoRepository.count() - dashboardAlunosAcessoVO.getQuantidadeAlunosNaFaculdade()));

        return dashboardAlunosAcessoVO;
    }

    public DashboardAula obterDadosAula(){
        DashboardAula dashboardAula = new DashboardAula();
        dashboardAula.setQuantidadeAulasAcontecendo(aulaRepository.countAulasAcontecendo());
        dashboardAula.setQuantidadeAulasNaoAcontecendo((int) gradeRepository.count() - dashboardAula.getQuantidadeAulasAcontecendo());

        return dashboardAula;
    }

    public List<DashboardRegistrosRecentesVO> obterRegistrosRecentes(){
        List<DashboardRegistrosRecentesVO> dashboardRegistrosRecentesVOList = new ArrayList<>();

        List<RegistrosRecentesVO> registrosRecentesVO = registrosRecentesRepository.findRegistrosRecentes();
        if(registrosRecentesVO.isEmpty()){
            dashboardRegistrosRecentesVOList.add(new DashboardRegistrosRecentesVO("Nenhum registro disponível"));
        } else{
            for(RegistrosRecentesVO rr : registrosRecentesVO){
                DashboardRegistrosRecentesVO dadoDashboard = new DashboardRegistrosRecentesVO();
                if(rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_ACESSO)){
                    // O ALUNO X ENTROU NA FACULDADE PELO DISPOSITIVO Y
                    dadoDashboard.setDescricao(String.format("O aluno %s entrou na faculdade pelo dispositovo %s", rr.getReferencia(), rr.getDescricao()));
                } else if(rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_AULA)){
                    // A AULA X, DA GRADE Y, FOI ABERTA NA SALA Z
                    String[] aulaIdSalaId = rr.getReferencia().split("_");
                    dadoDashboard.setDescricao(String.format("A aula %s, da grade %s, foi aberta na sala %s", aulaIdSalaId[0], rr.getDescricao(), aulaIdSalaId[1]));
                } else if(rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_PRESENCA)){
                    // O ALUNO X REGISTROU PRESENÇA NA AULA Y
                    dadoDashboard.setDescricao(String.format("O aluno %s registrou presença na aula %s", rr.getReferencia(), rr.getDescricao()));
                }
                dashboardRegistrosRecentesVOList.add(dadoDashboard);
            }
        }

        return dashboardRegistrosRecentesVOList;
    }
}
