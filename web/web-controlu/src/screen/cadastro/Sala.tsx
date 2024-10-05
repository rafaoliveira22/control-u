import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';


import Paper from '@mui/material/Paper';

import { Toaster, toast } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import { SalaProps } from '../../interface/SalaProps';
import DispositivoSelect from '../../components/DispositivoSelect';
import { obterTodasSalas, registrarDadosSala } from '../../http/HttpClientSala';

export default function Sala() {
  const [dispositivoSelecionado, setDispositivoSelecionado] = useState<number | string>('')
  const [salaNome, setSalaNome] = useState<string>('')
  const [salas, setSalas] = useState<SalaProps[]>([]);
  useEffect(() => {
    fetchDados();
  }, [])

  const fetchDados = async () => {
    try{
      const dados:  SalaProps[] = await obterTodasSalas();
      const dadosConvertidos = dados.map(sala => [
        sala.salaId,
        sala.salaNome,
        sala.dispositivoId
      ]);
      setSalas(dadosConvertidos)
    } catch (error) {
      
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao obter dados das salas! Tente novamente ou contate o suporte."
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
      const sala: SalaProps = {
        salaId: "indefinido",
        salaNome: salaNome,
        dispositivoId: dispositivoSelecionado as string
      };
  
      const promise = registrarDadosSala(sala);
      toast.promise(promise, {
        loading: "Cadastrando sala...",
        success: () => {
          setDispositivoSelecionado("")
          setSalaNome("")
          fetchDados()
          return "Cadastro realizado com sucesso"
        },
        error: (error) => {
          const messageError = "Erro ao cadastrar sala! Tente novamente ou contate o suporte.";
          if(error.message.toLowerCase() !== "O DISPOSITIVO escolhido já esta sendo utilizado em outra sala.".toLowerCase()){
            error.message = messageError
          } 

          return error.message;
        }
      })
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };

  const validarDadosFormulario = () => {
    if(!salaNome){
      return 'Nome'
    }

    if(!dispositivoSelecionado){
      return 'Dispositivo'
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
              label="Nome da Sala"
              name="nome"
              autoComplete="nome"
              autoFocus
              onChange={(e) => {setSalaNome(e.target.value as string)}}
              value={salaNome}
            />

            <DispositivoSelect value={dispositivoSelecionado} onChange={(e) => setDispositivoSelecionado(e.target.value as string)} isRelatorio={false}/>
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>Cadastrar</Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Salas Registradas" 
            campos={["ID", "Nome", "Dispositivo"]}
            dados={salas}
          />
        </Paper>
      </Grid>
    </>
  );
}