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

    public DashboardVO obterDadosDashboard(){
        return new DashboardVO(obterDadosAlunosAcesso(), obterDadosRegistrosRecentes(), obterDadosAula(), obterDadosAlunosAula());
    }


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

    public DashboardAulaVO obterDadosAula(){
        DashboardAulaVO dashboardAula = new DashboardAulaVO();
        dashboardAula.setQuantidadeAulasAcontecendo(aulaRepository.countAulasAcontecendo());
        dashboardAula.setQuantidadeAulasNaoAcontecendo((int) gradeRepository.count() - dashboardAula.getQuantidadeAulasAcontecendo());

        return dashboardAula;
    }

    public List<DashboardRegistrosRecentesVO> obterDadosRegistrosRecentes(){
        List<DashboardRegistrosRecentesVO> dashboardRegistrosRecentesVOList = new ArrayList<>();

        List<RegistrosRecentesVO> registrosRecentesVO = registrosRecentesRepository.findRegistrosRecentes();
        if(registrosRecentesVO.isEmpty()){
            dashboardRegistrosRecentesVOList.add(new DashboardRegistrosRecentesVO("0", "0", "Nenhum registro disponível"));
        } else{
            for(RegistrosRecentesVO rr : registrosRecentesVO){
                String acao = "indefinido";
                DashboardRegistrosRecentesVO dadoDashboard = new DashboardRegistrosRecentesVO();
                if(rr.getTipo().contains("ACESSO")){
                    // O ALUNO X (ENTROU NA/SAIU DA) FACULDADE PELO DISPOSITIVO Y
                    if(rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_ACESSO_ENTRADA)){
                        acao = "entrou na";
                    } else if (rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_ACESSO_SAIDA)) {
                        acao = "saiu da";
                    }
                    dadoDashboard.setDescricao(String.format("O aluno %s %s faculdade pelo dispositovo %s", rr.getReferencia(), acao, rr.getDescricao()));
                } else if(rr.getTipo().contains("AULA")){
                    // A AULA X, DA GRADE Y, FOI (ABERTA/FECHADA) NA SALA Z
                    String[] aulaIdSalaId = rr.getReferencia().split("_");

                    if(rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_AULA_FECHAMENTO)){
                        acao = "fechada";
                    } else if (rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_AULA_ABERTURA)) {
                        acao = "aberta";
                    }
                    dadoDashboard.setDescricao(String.format("A aula %s, da grade %s, foi %s na sala %s", aulaIdSalaId[0], rr.getDescricao(), acao, aulaIdSalaId[1]));
                }  else if(rr.getTipo().contains("PRESENCA")){
                    // O ALUNO X REGISTROU (PRESENÇA NA/SAIDA DA) AULA Y
                    if(rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_PRESENCA_ENTRADA)){
                        acao = "presença na";
                    } else if (rr.getTipo().equalsIgnoreCase(Defines.TIPO_REGISTRO_RECENTE_PRESENCA_SAIDA)) {
                        acao = "saída da";
                    }
                    dadoDashboard.setDescricao(String.format("O aluno %s registrou %s aula %s", rr.getReferencia(), acao, rr.getDescricao()));
                }
                dadoDashboard.setData(rr.getDataFormatada());
                dadoDashboard.setHorario(rr.getHorario());
                dashboardRegistrosRecentesVOList.add(dadoDashboard);
            }
        }

        return dashboardRegistrosRecentesVOList;
    }
}
