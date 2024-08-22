import { Grid, Box, Button, Paper, FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import React, { useState, useEffect } from 'react';
import { toast, Toaster } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import { DispositivoCadastroProps, DispositivoProps } from '../../interface/DispositivoProps';
import { obterDadosDeTodosDispositivos, registrarDispositivo } from '../../http/HttpClientDispositivo';
import config from '../../config/config';

export default function Dispositivo(){
  const [dispositivoTipo, setDispositivoTipo] = useState<number | string>('')
  const [dispositivos, setDispositivos] = useState<DispositivoProps[]>([])

  useEffect(() => {
    fecthDados();
  }, [])

  const fecthDados = async () => {
    try{
      const dados: DispositivoProps[] = await obterDadosDeTodosDispositivos();
      let dadosConvertidos: DispositivoProps[] = []
      dadosConvertidos = dados.map(dispositivo => [
        dispositivo.dispositivoId,
        config.dispositivos.status[dispositivo.dispositivoStatus],
      ])
      setDispositivos(dadosConvertidos)
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao obter os dispositivos registradas! Tente novamente ou contate o suporte."
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
      const dado: DispositivoCadastroProps = {
        dispositivoTipo: dispositivoTipo as number,
      };
      
      const promise = registrarDispositivo(dado);
      toast.promise(promise, {
        loading: 'Cadastrando dispositivo...',
        success: () => {
          setDispositivoTipo(0)
          fecthDados()
          return 'Cadastro realizado com sucesso'
        },
        error: (error) => {
          return "Erro ao cadastrar dispositivo! Tente novamente ou contate o suporte."
        }
      }) 
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };

  const validarDadosFormulario = () => {
    if(!dispositivoTipo){
      return 'Tipo do dispositivo'
    }

    return false;
  }

  return (
    <>
      <Grid item xs={12}>
        <Toaster richColors expand={true} closeButton />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
        <FormControl fullWidth>
          <Toaster richColors  expand={true} />
          <InputLabel id="select-label">Dispositivos *</InputLabel>
          <Select
            labelId="select-label"
            id="select"
            value={dispositivoTipo}
            label="Dispositivo"
            onChange={(e) => setDispositivoTipo(e.target.value as number)}
          >
            {Object.entries(config.dispositivos.tipos).map(([key, value]) => (
              <MenuItem key={key} value={key}>
                {value}
              </MenuItem>
            ))}
          </Select>
          </FormControl>
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>
              Cadastrar
            </Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Dispositivos de Leitura Registrados" 
            campos={["ID", "Status"]}
            dados={dispositivos}
          />
        </Paper>
      </Grid>
    </>
  );
}