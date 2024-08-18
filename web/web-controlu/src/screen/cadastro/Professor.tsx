import { Grid, Box, TextField, Button, Paper } from '@mui/material';
import React, { useState, useEffect } from 'react';
import { toast, Toaster } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import { ProfessorProps } from '../../interface/ProfessorProps';
import { obterTodosProfessores, registrarDadosProfessor } from '../../http/HttpClientProfessor';

export default function Professor(){
  const [professorNome, setProfessorNome] = useState("")
  const [professorId, setProfessorId] = useState("")
  const [professores, setProfessores] = useState<ProfessorProps[]>([])

  useEffect(() => {
    fecthDados();
  }, [])

  const fecthDados = async () => {
    try{
      const dados: ProfessorProps[] = await obterTodosProfessores();
      const dadosConvertidos = dados.map(professor => [
        professor.professorId,
        professor.professorNome,
      ]);
      setProfessores(dadosConvertidos)
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao obter dados dos professores! Tente novamente ou contate o suporte."
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
      const dado: ProfessorProps = {
        professorId: professorId,
        professorNome: professorNome
      };
  
   
      try {
        await registrarDadosProfessor(dado);
        toast.success("Cadastro realizado com sucesso");
        setProfessorId("")
        setProfessorNome("")

        fecthDados()
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
    if(!professorId){
      return 'ID'
    }

    if(!professorNome){
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
              id="professorNome"
              label="Nome do Professor"
              name="professorNome"
              autoComplete="nome"
              autoFocus
              onChange={(e) => {setProfessorNome(e.target.value as string)}}
              value={professorNome}
            />

            <TextField
              margin="normal"
              required
              fullWidth
              name="professorId"
              label="Identificador do Professor"
              type="text"
              id="professorId"
              autoComplete="id"
              onChange={(e) => {setProfessorId(e.target.value as string)}}
              value={professorId}
            />
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>
              Cadastrar
            </Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Professores Registrados" 
            campos={["ID", "Nome"]}
            dados={professores}
          />
        </Paper>
      </Grid>
    </>
  );
}