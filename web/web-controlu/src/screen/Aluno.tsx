import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';


import Paper from '@mui/material/Paper';
import Orders from '../components/dashboard/Orders';

import { Toaster, toast } from 'sonner';
import { AlunoProps } from '../interface/AlunoProps';
import { registrarDadosAluno } from '../http/HttpClientAluno';

export default function Aluno() {

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const aluno: AlunoProps = {
      nome: data.get('nome') as string,
      id: data.get('id') as string,
    };
    console.log(aluno)

    try {
      await registrarDadosAluno(aluno);
      toast.success("Cadastro realizado com sucesso");
    } catch (error) {
      toast.error("Erro ao cadastrar aluno");
    }
    
  };

  return (
    <>
        <Grid item xs={12}>
          <Toaster richColors  expand={true} />
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
            />

            <TextField
              margin="normal"
              required
              fullWidth
              name="id"
              label="R.A"
              type="text"
              id="id"
              autoComplete="ra"
            />

            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>
              Cadastrar
            </Button>
          </Box>
          </Grid>

          {/* Registros */}
          <Grid item xs={12}>
            <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
              <Orders />
            </Paper>
          </Grid>
      </>
  );
}