import { Grid, Box, TextField, Button, Paper } from '@mui/material';
import React, { useState, useEffect } from 'react';
import { toast, Toaster } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import { DisciplinaProps } from '../../interface/DisciplinaProps';
import { obterDadosDeTodasDisciplinas, registrarDisciplina } from '../../http/HttpClientDisciplina';

export default function Disciplina(){
  const [disciplinaNome, setDisciplinaNome] = useState("")
  const [disciplinaId, setDisciplinaId] = useState("")
  const [disciplinas, setDisciplinas] = useState<DisciplinaProps[]>([])

  useEffect(() => {
    fecthDados();
  }, [])

  const fecthDados = async () => {
    try{
      const dados: DisciplinaProps[] = await obterDadosDeTodasDisciplinas();
      const dadosConvertidos = dados.map(disciplina => [
        disciplina.disciplinaId,
        disciplina.disciplinaNome,
      ]);
      setDisciplinas(dadosConvertidos)
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao obter as disciplinas registradas! Tente novamente ou contate o suporte."
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
      const dado: DisciplinaProps = {
        disciplinaId: disciplinaId,
        disciplinaNome: disciplinaNome
      };
      
      const promise = registrarDisciplina(dado);
      toast.promise(promise, {
        loading: 'Cadastrando disciplina...',
        success: () => {
          setDisciplinaId("")
          setDisciplinaNome("")
          fecthDados()
        },
        error: (error) => {
          if(error.message.toLowerCase() !== "registro já existe!"){
            error.message = "Erro ao cadastrar disciplina! Tente novamente ou contate o suporte."
          } 

          return error.message
        }
      })
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };

  const validarDadosFormulario = () => {
    if(!disciplinaId){
      return 'ID'
    }

    if(!disciplinaNome){
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
              id="disciplinaId"
              label="Identificador da Disciplina"
              name="disciplinaId"
              autoComplete="id"
              autoFocus
              onChange={(e) => {setDisciplinaId(e.target.value as string)}}
              value={disciplinaId}
            />

            <TextField
              margin="normal"
              required
              fullWidth
              name="disciplinaNome"
              label="Nome da Disciplina"
              type="text"
              id="disciplinaNome"
              autoComplete="nome"
              onChange={(e) => {setDisciplinaNome(e.target.value as string)}}
              value={disciplinaNome}
            />
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>
              Cadastrar
            </Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Disciplinas Registradas" 
            campos={["ID", "Nome"]}
            dados={disciplinas}
          />
        </Paper>
      </Grid>
    </>
  );
}