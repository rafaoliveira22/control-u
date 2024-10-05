import React, { useEffect, useState } from 'react';
import { CardDashboard } from '../components/CardDashboard';
import { obterDadosDashboard } from '../http/HttpClientDashboard';
import { toast, Toaster } from 'sonner';
import { DashboardRegistrosRecentesProps } from '../interface/DashBoardProps';
import { RegistrosRecentesDashboard } from '../components/RegistrosRecentesDashboard';
import { Box, CircularProgress, Grid, Typography } from '@mui/material';

export default function Dashboard() {
  const [loading, setLoading] = useState<boolean>(true);
  const [quantidadeAlunosEmAula, setQuantidadeAlunosEmAula] = useState<number>(0);
  const [quantidadeAlunosForaDeAula, setQuantidadeAlunosForaDeAula] = useState<number>(0);
  const [quantidadeAlunosNaFaculdade, setQuantidadeAlunosNaFaculdade] = useState<number>(0);
  const [quantidadeAlunosForaDaFaculdade, setQuantidadeAlunosForaDaFaculdade] = useState<number>(0);
  const [quantidadeAulasAcontecendo, setQuantidadeAulasAcontecendo] = useState<number>(0);
  const [quantidadeAulasNaoAcontecendo, setQuantidadeAulasNaoAcontecendo] = useState<number>(0);
  const [registrosRecentes, setRegistrosRecentes] = useState<DashboardRegistrosRecentesProps>();

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const dados = await obterDadosDashboard();

        setQuantidadeAlunosEmAula(dados.dashboardAlunosAulaVO.quantidadeAlunosEmAula);
        setQuantidadeAlunosForaDeAula(dados.dashboardAlunosAulaVO.quantidadeAlunosForaDeAula);
        setQuantidadeAlunosNaFaculdade(dados.dashboardAlunosAcessoVO.quantidadeAlunosNaFaculdade);
        setQuantidadeAlunosForaDaFaculdade(dados.dashboardAlunosAcessoVO.quantidadeAlunosForaDaFaculdade);
        setQuantidadeAulasAcontecendo(dados.dashboardAulaVO.quantidadeAulasAcontecendo);
        setQuantidadeAulasNaoAcontecendo(dados.dashboardAulaVO.quantidadeAulasNaoAcontecendo);
        setRegistrosRecentes(dados.dashboardRegistrosRecentesVOList);
      } catch (error) {
        if (error instanceof Error) {
          toast.error(error.message);
        } else {
          toast.error('Erro desconhecido');
        }
      } finally {
        setLoading(false);
      }
    };
    fetchDados();
  }, []);


  return (
    <>
      <Toaster richColors expand={true} closeButton />
      {loading ? (
        <Box
          display="flex"
          flexDirection="column"
          alignItems="center"
          justifyContent="center"
          height="100vh"
          ml={4}
        >
          <CircularProgress />
          <Typography variant="h5" style={{ marginTop: '16px' }}>
            Carregando...
          </Typography>
        </Box>
      ) : (
        <Grid container spacing={4} ml={4}>
          <Grid item xs={12} sm={3}>
            <CardDashboard
              titulo='Alunos'
              subtitulo='aula'
              quantidade={quantidadeAlunosEmAula}
              quantidadeOposto={quantidadeAlunosForaDeAula}
            />
          </Grid>

          <Grid item xs={12} sm={3}>
            <CardDashboard
              titulo='Alunos'
              subtitulo='acesso'
              quantidade={quantidadeAlunosNaFaculdade}
              quantidadeOposto={quantidadeAlunosForaDaFaculdade}
            />
          </Grid>

          <Grid item xs={12} sm={3}>
            <CardDashboard
              titulo='Aulas'
              subtitulo='acontecendo'
              quantidade={quantidadeAulasAcontecendo}
              quantidadeOposto={quantidadeAulasNaoAcontecendo}
            />
          </Grid>

          {registrosRecentes ? (
            <Grid item xs={12}>
              <RegistrosRecentesDashboard dados={registrosRecentes} />
            </Grid>
          ) : null}
        </Grid>
      )}
    </>
  );
}
