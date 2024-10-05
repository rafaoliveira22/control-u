import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { DisciplinaProps } from '../interface/DisciplinaProps';
import { obterDadosDeTodasDisciplinas } from '../http/HttpClientDisciplina';

const DisciplinaSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [disciplinas, setDisciplinas] = useState<DisciplinaProps[]>([]);

  const fetchDados = async () => {
    try {
      const data = await obterDadosDeTodasDisciplinas();
      setDisciplinas(data);
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao carregar as disciplinas disponíveis! Tente novamente ou contate o suporte."
        }
        toast.error(error.message);
        const disciplinaError: DisciplinaProps = {
          disciplinaId: "0" as string,
          disciplinaNome: error.message,

        };
        setDisciplinas([disciplinaError]);
      } else {
        toast.error('Erro desconhecido');
      }
    }
  };

  useEffect(() => {
    fetchDados();
  }, []);

  return (
    <FormControl fullWidth sx={{ mb: 2 }}>
      <Toaster richColors  expand={true} />
      <InputLabel id="select-label">Disciplina *</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Disciplina"
        onChange={onChange}
      >
        {disciplinas.map((disciplina) => (
          <MenuItem key={disciplina.disciplinaId} value={disciplina.disciplinaId}>
            {disciplina.disciplinaNome} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default DisciplinaSelect;
