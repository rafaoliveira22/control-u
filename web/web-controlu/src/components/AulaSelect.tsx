import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { obterDadosTodasAulas } from '../http/HttpClientAula';
import { AulaProps } from '../interface/AulaProps';

const AulaSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [aulas, setAulas] = useState<AulaProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterDadosTodasAulas();
        if(isRelatorio){
          const dadosSelect = [{aulaId: 0, aulaAbertura: 'Todos', aulaFechamento: '', gradeId: 0, salaId: ''}, ...data]
          setAulas(dadosSelect);
        } else{
          setAulas(data);
        }
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar as aulas disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const erroPersonalizado: AulaProps = {
            aulaId: 0, aulaAbertura: 'Erro ao obter as aulas', aulaFechamento: '', gradeId: 0, salaId: ''
          };
          setAulas([erroPersonalizado]);
        } else {
          toast.error('Erro desconhecido');
        }
      }
    };

    fetchDados();
  }, []);

  return (
    <FormControl fullWidth sx={{ mb: 2 }}>
      <Toaster richColors  expand={true} />
      <InputLabel id="select-label">{isRelatorio ? 'Aula ID' : 'Aula ID *'}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Aula"
        onChange={onChange}
      >
        {aulas.map((aula) => (
          <MenuItem key={aula.aulaId} value={aula.aulaId}>
            {`${aula.aulaId} - ${aula.aulaAbertura} - ${aula.salaId}`} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default AulaSelect;
