import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import InputMask from 'react-input-mask';

import { Toaster, toast } from 'sonner';
import { gerarRelatorio } from '../http/HttpClientRelatorio';
import TipoRelatorioSelect from '../components/TipoRelatorioSelect';
import AlunoSelect from '../components/AlunoSelect';
import config from '../config/config';
import { FiltroRelatorioAcesso, FiltroRelatorioAula, FiltroRelatorioPresenca } from '../interface/RelatorioProps';
import GradeSelect from '../components/GradeSelect';
import SalaSelect from '../components/SalaSelect';
import ProfessorSelect from '../components/ProfessorSelect';
import AcessoSelect from '../components/AcessoSelect';
import AulaSelect from '../components/AulaSelect';
import PresencaSelect from '../components/PresencaSelect';
import DispositivoSelect from '../components/DispositivoSelect';
import { Typography } from '@mui/material';

export default function Relatorio() { 
  useEffect(() => {
    fetchDados();
  }, [])

  const [dataInicial, setDataInicial] = useState<string>('')
  const [dataFinal, setDataFinal] = useState<string>('')
  const [tipoRelatorioSelecionado, setTipoRelatorioSelecionado] = useState<string | string>('')
  const [alunoSelecionado, setAlunoSelecionado] = useState<string | string>('')
  
  const [acessoIdSelecionado, setAcessoIdSelecionado] = useState<number | string>('')
  const [aulaIdSelecionado, setAulaIdSelecionado] = useState<string | string>('')
  const [presencaIdSelecionado, setPresencaIdSelecionado] = useState<number | string>('')
  
  const [gradeIdSelecionado, setGradeIdSelecionado] = useState<string | string>('')
  const [salaIdSelecionado, setSalaIdSelecionado] = useState<string | string>('')
  const [professorIdSelecionado, setProfessorIdSelecionado] = useState<string | string>('')
  const [dispositivoIdSelecionado, setDispositivoIdSelecionado] = useState<string | string>('')

  const fetchDados = async () => {

  }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if(tipoRelatorioSelecionado === '' || tipoRelatorioSelecionado === null || tipoRelatorioSelecionado === undefined){
      toast.error("Tipo de relatório inválido! Selecione ACESSO, AULA ou PRESENÇA.");
    } else{
      let filtroRelatorio: FiltroRelatorioAcesso | FiltroRelatorioAula | FiltroRelatorioPresenca | null = null;
      if (tipoRelatorioSelecionado === config.tipo_relatorio.ACESSO) {
        filtroRelatorio = {
          tipo: tipoRelatorioSelecionado,
          dataInicial: dataInicial === '' || dataInicial === null ? null : dataInicial,
          dataFinal: dataFinal === '' || dataFinal === null ? null : dataFinal,
          alunoId: alunoSelecionado === '' || alunoSelecionado === null || alunoSelecionado === '0' ? null : alunoSelecionado,
          acessoId: acessoIdSelecionado === '' || acessoIdSelecionado === null || acessoIdSelecionado === 0 ? null : acessoIdSelecionado,
          dispositivoId: dispositivoIdSelecionado === '' || dispositivoIdSelecionado === null || dispositivoIdSelecionado === '0' ? null : dispositivoIdSelecionado    
        } as FiltroRelatorioAcesso;
        
      } else if (tipoRelatorioSelecionado === config.tipo_relatorio.AULA) {
        filtroRelatorio = {
          tipo: tipoRelatorioSelecionado,
          dataInicial: dataInicial === '' || dataInicial === null ? null : dataInicial,
          dataFinal: dataFinal === '' || dataFinal === null ? null : dataFinal,
          alunoId: alunoSelecionado === '' || alunoSelecionado === null || alunoSelecionado == '0' ? null : alunoSelecionado,
          aulaId: aulaIdSelecionado === '' || aulaIdSelecionado === null || aulaIdSelecionado == '0' ? null : aulaIdSelecionado,
          gradeId: gradeIdSelecionado === '' || gradeIdSelecionado === null || gradeIdSelecionado == '0' ? null : gradeIdSelecionado,
          salaId: salaIdSelecionado === '' || salaIdSelecionado === null || salaIdSelecionado == '0' ? null : salaIdSelecionado,
          professorId: professorIdSelecionado === '' || professorIdSelecionado === null || professorIdSelecionado == '0' ? null : professorIdSelecionado
        } as FiltroRelatorioAula;
      
      } else if (tipoRelatorioSelecionado === config.tipo_relatorio.PRESENÇA) {
        filtroRelatorio = {
          tipo: tipoRelatorioSelecionado,
          dataInicial: dataInicial === '' || dataInicial === null ? null : dataInicial,
          dataFinal: dataFinal === '' || dataFinal === null ? null : dataFinal,
          alunoId: alunoSelecionado === '' || alunoSelecionado === null || alunoSelecionado === '0' ? null : alunoSelecionado,
          presencaId: presencaIdSelecionado === '' || presencaIdSelecionado === null || presencaIdSelecionado == 0 ? null : presencaIdSelecionado,
          aulaId: aulaIdSelecionado === '' || aulaIdSelecionado === null || aulaIdSelecionado == '0' ? null : aulaIdSelecionado,
          gradeId: gradeIdSelecionado === '' || gradeIdSelecionado === null || gradeIdSelecionado == '0' ? null : gradeIdSelecionado,
        } as FiltroRelatorioPresenca;
      } else{
        toast.error("Tipo de relatório inválido! Selecione ACESSO, AULA ou PRESENÇA.")
        return
      }
      const promise = gerarRelatorio(filtroRelatorio)
      toast.promise(promise, {
        loading: "Gerando relatório...",
        success: (response) => {
          const url = window.URL.createObjectURL(response);
          window.open(url);
          return "Relatório gerado com sucesso!"
        },
        error: (error) => {
          if(error.message.toLowerCase().includes('json')){
             error.message = 'Erro ao gerar relatório! Tente novamente ou contate o suporte.\nCaso permaneça, considere fazer login novamente.' 
          }
          return error.message;
        }
      })
    }
  }

  return (
    <>
      <Grid item xs={12}>
        <Toaster richColors expand={true} closeButton />
        <Box component="div" >
          <Typography variant="h6" gutterBottom>Instruções</Typography>
          <Typography variant="body1" gutterBottom>- Escolha o tipo de relatório: <strong>Acesso, Aula ou Presença.</strong> </Typography>
          <Typography variant="body1" gutterBottom>- <strong>Se atente aos filtros que você aplicar</strong>, para que as informações apareçam corretamente, de acordo com o esperado.</Typography>
          <Typography variant="body1" gutterBottom>
            - Caso não passe valor para os filtros, será aplicado o valor padrão de "Todos". No caso das datas, caso não informado, considera-se 
            todos os registros até a data atual.
          </Typography>
        </Box>

        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 3 }}>
          <TipoRelatorioSelect value={tipoRelatorioSelecionado} onChange={(e) => setTipoRelatorioSelecionado(e.target.value as string)} isRelatorio={true}/>
          <InputMask mask="99/99/9999" value={dataInicial} disabled={false} onChange={(e) => {setDataInicial(e.target.value)}}>
            <TextField margin="normal" fullWidth name="data_inicial" label="Data Inicial" type="text" id="data_inicial" autoComplete="data"/>
          </InputMask>

          <InputMask mask="99/99/9999" value={dataFinal} disabled={false} onChange={(e) => {setDataFinal(e.target.value)}}>
            <TextField margin="normal" fullWidth name="data_final" label="Data Final" type="text" id="data_final" autoComplete="data" sx={{ mb: 2 }}/>
          </InputMask>
          
          {/** Os filtros são dinâmicos, ou seja, aparecem de acordo com o tipo de relatório selecionado */}
          {tipoRelatorioSelecionado === config.tipo_relatorio.ACESSO ? 
            <AcessoSelect value={acessoIdSelecionado} onChange={(e) => setAcessoIdSelecionado(e.target.value as number)} isRelatorio={true} /> 
            : null}
          {tipoRelatorioSelecionado === config.tipo_relatorio.AULA || tipoRelatorioSelecionado === config.tipo_relatorio.PRESENÇA ? 
            <AulaSelect value={aulaIdSelecionado} onChange={(e) => setAulaIdSelecionado(e.target.value as string)} isRelatorio={true} /> 
            : null}

          {tipoRelatorioSelecionado === config.tipo_relatorio.PRESENÇA ? 
            <PresencaSelect value={presencaIdSelecionado} onChange={(e) => setPresencaIdSelecionado(e.target.value as number)} isRelatorio={true} /> 
            : null}

          {tipoRelatorioSelecionado === config.tipo_relatorio.ACESSO || tipoRelatorioSelecionado === config.tipo_relatorio.PRESENÇA ? 
            <AlunoSelect value={alunoSelecionado} onChange={(e) => setAlunoSelecionado(e.target.value as string)} isRelatorio={true} />
          : null}
          
          {tipoRelatorioSelecionado === config.tipo_relatorio.PRESENÇA || tipoRelatorioSelecionado === config.tipo_relatorio.AULA ? 
            <GradeSelect value={gradeIdSelecionado} onChange={(e) => setGradeIdSelecionado(e.target.value as string)} isRelatorio={true} />
            :null}

          {tipoRelatorioSelecionado === config.tipo_relatorio.AULA ?
            <>
              <ProfessorSelect value={professorIdSelecionado} onChange={(e) => setProfessorIdSelecionado(e.target.value as string)} isRelatorio={true} />
              <DispositivoSelect value={dispositivoIdSelecionado} onChange={(e) => setDispositivoIdSelecionado(e.target.value as string)} isRelatorio={true} />
            </>
            :null}

          {tipoRelatorioSelecionado === config.tipo_relatorio.AULA ? 
            <SalaSelect value={salaIdSelecionado} onChange={(e) => setSalaIdSelecionado(e.target.value as string)} isRelatorio={true} />
          :null}
          <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>Gerar Relatório</Button>
        </Box>
      </Grid>
    </>
  );
}