package com.controlu.backend.vo;

import java.util.List;

public class DashboardVO {
    private DashboardAlunosAcessoVO dashboardAlunosAcessoVO;
    private List<DashboardRegistrosRecentesVO> dashboardRegistrosRecentesVOList;
    private DashboardAulaVO dashboardAulaVO;
    private DashboardAlunosAulaVO dashboardAlunosAulaVO;

    public DashboardVO(){}

    public DashboardVO(DashboardAlunosAcessoVO dashboardAlunosAcessoVO, List<DashboardRegistrosRecentesVO> dashboardRegistrosRecentesVOList, DashboardAulaVO dashboardAulaVO, DashboardAlunosAulaVO dashboardAlunosAulaVO) {
        this.dashboardAlunosAcessoVO = dashboardAlunosAcessoVO;
        this.dashboardRegistrosRecentesVOList = dashboardRegistrosRecentesVOList;
        this.dashboardAulaVO = dashboardAulaVO;
        this.dashboardAlunosAulaVO = dashboardAlunosAulaVO;
    }

    public DashboardAlunosAcessoVO getDashboardAlunosAcessoVO() {
        return dashboardAlunosAcessoVO;
    }

    public void setDashboardAlunosAcessoVO(DashboardAlunosAcessoVO dashboardAlunosAcessoVO) {
        this.dashboardAlunosAcessoVO = dashboardAlunosAcessoVO;
    }

    public List<DashboardRegistrosRecentesVO> getDashboardRegistrosRecentesVOList() {
        return dashboardRegistrosRecentesVOList;
    }

    public void setDashboardRegistrosRecentesVOList(List<DashboardRegistrosRecentesVO> dashboardRegistrosRecentesVOList) {
        this.dashboardRegistrosRecentesVOList = dashboardRegistrosRecentesVOList;
    }

    public DashboardAulaVO getDashboardAulaVO() {
        return dashboardAulaVO;
    }

    public void setDashboardAulaVO(DashboardAulaVO dashboardAulaVO) {
        this.dashboardAulaVO = dashboardAulaVO;
    }

    public DashboardAlunosAulaVO getDashboardAlunosAulaVO() {
        return dashboardAlunosAulaVO;
    }

    public void setDashboardAlunosAulaVO(DashboardAlunosAulaVO dashboardAlunosAulaVO) {
        this.dashboardAlunosAulaVO = dashboardAlunosAulaVO;
    }
}
