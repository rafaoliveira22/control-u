import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import InputMask from 'react-input-mask';


import { Toaster, toast } from 'sonner';
import { gerarRelatorio } from '../http/HttpClientRelatorio';
import TipoRelatorioSelect from '../components/TipoRelatorioSelect';

interface FiltroRelatorioVO {
  tipo: string
  dataInicial: string;
  dataFinal: string;
  alunoId?: string | null;
  aulaId?: number | null;
}

export default function Relatorio() { 
  useEffect(() => {
    fetchDados();
  }, [])

  const [dataInicial, setDataInicial] = useState<string>('')
  const [dataFinal, setDataFinal] = useState<string>('')
  const [tipoRelatorioSelecionado, setTipoRelatorioSelecionado] = useState<string | string>('')


  const fetchDados = async () => {

  }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const filtroRelatorio: FiltroRelatorioVO = {
      tipo: "PRESENÇA",
      dataInicial: "01/01/2024",
      dataFinal: "30/09/2024",
      alunoId: null,
      aulaId: null    
    };

    const response = await gerarRelatorio(filtroRelatorio)
    const url = window.URL.createObjectURL(response);
    window.open(url);
  };

  return (
    <>
      <Grid item xs={12}>
        <Toaster richColors expand={true} closeButton />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
          <TipoRelatorioSelect value={tipoRelatorioSelecionado} onChange={(e) => setTipoRelatorioSelecionado(e.target.value as string)} />
          <InputMask mask="99/99/9999" value={dataInicial} disabled={false} onChange={(e) => {setDataInicial(e.target.value)}}>
            <TextField margin="normal" fullWidth name="data_inicial" label="Data Inicial" type="text" id="data_inicial" autoComplete="data"/>
          </InputMask>

          <InputMask mask="99/99/9999" value={dataFinal} disabled={false} onChange={(e) => {setDataFinal(e.target.value)}}>
            <TextField margin="normal" fullWidth name="data_final" label="Data Final" type="text" id="data_final" autoComplete="data"/>
          </InputMask>


          <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>Gerar Relatório</Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        Relatório
      </Grid>
    </>
  );
}