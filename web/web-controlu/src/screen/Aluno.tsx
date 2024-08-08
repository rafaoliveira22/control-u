import React, { useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';


import Paper from '@mui/material/Paper';
import Orders from '../components/dashboard/Orders';

import { Toaster, toast } from 'sonner';
import { AlunoProps } from '../interface/AlunoProps';
import { registrarDadosAluno } from '../http/HttpClientAluno';
import CursoSelect from '../components/CursoSelect';

export default function Aluno() {
  const [cursoSelecionado, setCursoSelecionado] = useState<number | string>('')
  const [nome, setNome] = useState<string>('')
  const [ra, setRa] = useState<string>('')
  const [anoIngressao, setAnoIngressao] = useState('')


  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const validacaoDadosForms = validarDadosFormulario()
    if(!validacaoDadosForms){
      const aluno: AlunoProps = {
        alunoRa: ra,
        id: {
          alunoNome: nome,
          cursoId: Number(cursoSelecionado),
          alunoAnoIngressao: Number(anoIngressao),
        }
      };
  
  
      try {
        await registrarDadosAluno(aluno);
        toast.success("Cadastro realizado com sucesso");
      } catch (error) {
        if (error instanceof Error) {
          toast.error(error.message);
        } else {
          toast.error('Erro desconhecido');
        }
      }
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };


  const validarDadosFormulario = () => {
    if(!nome){
      return 'Nome'
    }

    if(!ra){
      return 'R.A'
    }

    if(!anoIngressao){
      return 'Ano de Ingressão'
    } else{
      const anoConvertido = parseInt(anoIngressao, 10);
      console.log(anoConvertido)
      console.log(isNaN(anoConvertido))
      if (isNaN(anoConvertido) && anoConvertido > 1900 && anoConvertido <= new Date().getFullYear()) {
        return 'Ano de Ingressão'
      }

    }

    if(!cursoSelecionado){
      return 'Curso'
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
              id="nome"
              label="Nome do aluno"
              name="nome"
              autoComplete="nome"
              autoFocus
              onChange={(e) => {setNome(e.target.value as string)}}
              value={nome}
            />

            <TextField
              margin="normal"
              required
              fullWidth
              name="ra"
              label="R.A"
              type="text"
              id="ra"
              autoComplete="ra"
              onChange={(e) => {setRa(e.target.value as string)}}
              value={ra}
            />
            <CursoSelect value={cursoSelecionado} onChange={(e) => setCursoSelecionado(e.target.value as number)} />

            <TextField
              margin="normal"
              required
              fullWidth
              name="anoIngressao"
              label="Ano Ingressão"
              type="number"
              id="anoIngressao"
              autoComplete="ano"
              onChange={(e) => {setAnoIngressao(e.target.value)}}
              value={anoIngressao}
            />
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>
              Cadastrar
            </Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <Orders />
        </Paper>
      </Grid>
    </>
  );
}