import React, { useEffect, useState } from 'react';
import RegistrosRecentesDashboard from '../components/RegistrosRecentesDashboard';
import { CardDashboard } from '../components/CardDashboard';
import { obterDadosAlunosAula, obterDadosAlunosAcesso, obterDadosAula } from '../http/HttpDashboard';
import { toast, Toaster } from 'sonner';

export default function Dashboard() {
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    fetchDados();
  }, []);

  // Dashboard Aluno-Aula
  const [quantidadeAlunosEmAula, setQuantidadeAlunosEmAula] = useState<number>(0);
  const [quantidadeAlunosForaDeAula, setQuantidadeAlunosForaDeAula] = useState<number>(0);

  // Dashboard Aluno-Acesso
  const [quantidadeAlunosNaFaculdade, setQuantidadeAlunosNaFaculdade] = useState<number>(0);
  const [quantidadeAlunosForaDaFaculdade, setQuantidadeAlunosForaDaFaculdade] = useState<number>(0);

  // Dashboard Aula
  const [quantidadeAulasAcontecendo, setQuantidadeAulasAcontecendo] = useState<number>(0);
  const [quantidadeAulasNaoAcontecendo, setQuantidadeAulasNaoAcontecendo] = useState<number>(0);

  const fetchDados = async () => {
    setLoading(true)

    try {
      const dadosDashboardAlunoAula = await obterDadosAlunosAula();
      const dadosDashboardAlunoAcesso = await obterDadosAlunosAcesso();
      const dadosDashboardAula = await obterDadosAula();

      setQuantidadeAlunosEmAula(dadosDashboardAlunoAula.quantidade);
      setQuantidadeAlunosForaDeAula(dadosDashboardAlunoAula.quantidadeOposto);

      setQuantidadeAlunosNaFaculdade(dadosDashboardAlunoAcesso.quantidade);
      setQuantidadeAlunosForaDaFaculdade(dadosDashboardAlunoAcesso.quantidadeOposto);

      setQuantidadeAulasAcontecendo(dadosDashboardAula.quantidade);
      setQuantidadeAulasNaoAcontecendo(dadosDashboardAula.quantidadeOposto);
    } catch (error) {
      if (error instanceof Error) {
        toast.error(error.message)
      } else {
        toast.error('Erro desconhecido')
      }
    } finally {
      setLoading(false)
    }
  };

  return (
    <>
      {loading ? (
        <div style={{ textAlign: 'center', marginTop: '20%' }}>
          <h2>Carregando...</h2>
        </div>
      ) : (
        <>
          <Toaster richColors expand={true} closeButton />
          <CardDashboard 
            titulo='Alunos' 
            subtitulo='aula' 
            quantidade={quantidadeAlunosEmAula} 
            quantidadeOposto={quantidadeAlunosForaDeAula}
          />
          
          <CardDashboard 
            titulo='Alunos' 
            subtitulo='acesso' 
            quantidade={quantidadeAlunosNaFaculdade} 
            quantidadeOposto={quantidadeAlunosForaDaFaculdade}
          />

          <CardDashboard 
            titulo='Aulas' 
            subtitulo='acontecendo' 
            quantidade={quantidadeAulasAcontecendo} 
            quantidadeOposto={quantidadeAulasNaoAcontecendo}
          />

          <RegistrosRecentesDashboard />
        </>
      )}
    </>
  )
}
