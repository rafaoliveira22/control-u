import { Grid, Box, TextField, Button, Paper } from '@mui/material';
import React, { useState, useEffect } from 'react';
import { toast, Toaster } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import { CursoProps } from '../../interface/CursoProps';
import { obterTodosCursos, registrarDadosCurso } from '../../http/HttpClientCurso';

export default function Curso(){
  const [cursoNome, setCursoNome] = useState("")
  const [cursos, setCursos] = useState<CursoProps[]>([])

  useEffect(() => {
    fecthDados();
  }, [])

  const fecthDados = async () => {
    try{
      const dados: CursoProps[] = await obterTodosCursos();
      const dadosConvertidos = dados.map(curso => [
        curso.cursoId,
        curso.cursoNome
      ]);
      setCursos(dadosConvertidos)
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao obter dados dos cursos! Tente novamente ou contate o suporte."
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
      const dado: CursoProps = {
        cursoNome: cursoNome
      };
  
      const promise = registrarDadosCurso(dado);
      toast.promise(promise, {
        loading: 'Cadastrando curso...',
        success: () => {
          setCursoNome("")
          fecthDados()

          return "Cadastro realizado com sucesso"
        },
        error: (error) => {
          if(error.message.toLowerCase() !== "registro já existe."){
            error.message = "Erro ao cadastrar curso! Tente novamente ou contate o suporte."
          }

          return error.message
        }
      })
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };

  const validarDadosFormulario = () => {
    if(!cursoNome){
      return 'Nome'
    }

    return false;
  }

  return (
    <>
      <Grid item xs={12}>
        <Toaster richColors expand={true} closeButton />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="cursoNome"
              label="Nome do Curso"
              name="cursoNome"
              autoComplete="nome"
              autoFocus
              onChange={(e) => {setCursoNome(e.target.value as string)}}
              value={cursoNome}
            />
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>
              Cadastrar
            </Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Cursos Registrados" 
            campos={["ID", "Nome"]}
            dados={cursos}
          />
        </Paper>
      </Grid>
    </>
  );
}