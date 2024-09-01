import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';


import Paper from '@mui/material/Paper';

import { Toaster, toast } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import { GradeCadastroProps, GradeProps } from '../../interface/GradeProps';
import { obterDadosDeTodasGrades, registrarDadosGrade } from '../../http/HttpClientGrade';
import CursoSelect from '../../components/CursoSelect';
import DisciplinaSelect from '../../components/DisciplinaSelect';
import ProfessorSelect from '../../components/ProfessorSelect';
import CartaoSelect from '../../components/CartaoSelect';
import config from '../../config/config';

export default function Grade() {
  const [cursoSelecionado, setCursoSelecionado] = useState<number | string>('')
  const [disciplinaSelecionada, setDisciplinaSelecionada] = useState<number | string>('')
  const [professorSelecionado, setProfessorSelecionado] = useState<number | string>('')
  const [cartaoSelecionado, setCartaoSelecionado] = useState<number | string>('')
  const [grades, setGrades] = useState<GradeProps[]>([]);
  useEffect(() => {
    fetchDados();
  }, [])

  const fetchDados = async () => {
    try{
      const dados: GradeProps[] = await obterDadosDeTodasGrades();
      const dadosConvertidos = dados.map(grade => [
        grade.gradeId as number, 
        config.cursos[grade.cursoId],
        grade.disciplinaId as string,
        grade.professorId as string,
        grade.cartaoId as string
      ]);
      setGrades(dadosConvertidos)
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao obter dados das grades de aula! Tente novamente ou contate o suporte."
        } 
        toast.error(error.message)
      } else {
        toast.error('Erro desconhecido')
      }
    }
  }
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const validacaoDadosForms = validarDadosFormulario()
    if(!validacaoDadosForms){
      const grade: GradeCadastroProps = {
        cursoId: cursoSelecionado as number,
        disciplinaId: disciplinaSelecionada as string,
        professorId: professorSelecionado as string,
        cartaoId: cartaoSelecionado as string
      };
  
      const promise = registrarDadosGrade(grade);
      toast.promise(promise, {
        loading: "Cadastrando grade...",
        success: () => {
          setCursoSelecionado("")
          setCartaoSelecionado("")
          setDisciplinaSelecionada("")
          setProfessorSelecionado("")
          fetchDados()
          return "Cadastro realizado com sucesso"
        },
        error: (error) => {
          if(error.message.toLowerCase() !== "O Cartão de Leitura escolhido já esta sendo utilizado em outra grade.".toLowerCase()){
            error.message = "Erro ao cadastrar grade! Tente novamente ou contate o suporte.";
          } 

          return error.message;
        },
      })
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };

  const validarDadosFormulario = () => {
    if(!cursoSelecionado){
      return 'Curso'
    }

    if(!disciplinaSelecionada){
      return 'Disciplina'
    }

    if(!cartaoSelecionado){
      return 'Cartão'
    }

    if(!professorSelecionado){
      return 'Professor'
    }

    return false;
  }

  return (
    <>
      <Grid item xs={12}>
        <Toaster richColors expand={true} closeButton />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <CursoSelect value={cursoSelecionado} onChange={(e) => setCursoSelecionado(e.target.value as number)} />
            <DisciplinaSelect value={disciplinaSelecionada} onChange={(e) => setDisciplinaSelecionada(e.target.value as string)} />
            <ProfessorSelect value={professorSelecionado} onChange={(e) => setProfessorSelecionado(e.target.value as string)} />
            <CartaoSelect value={cartaoSelecionado} onChange={(e) => setCartaoSelecionado(e.target.value as string)} />
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>Cadastrar</Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Grades Registradas" 
            campos={["ID", "Curso", "Disciplina", "Professor", "Cartão de Leitura"]}
            dados={grades}
          />
        </Paper>
      </Grid>
    </>
  );
}