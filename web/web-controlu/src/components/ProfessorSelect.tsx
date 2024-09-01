import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { ProfessorProps } from '../interface/ProfessorProps';
import { obterTodosProfessores } from '../http/HttpClientProfessor';

const ProfessorSelect: React.FC<SelectProps> = ({ value, onChange }) => {
  const [professores, setProfessores] = useState<ProfessorProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterTodosProfessores();
        setProfessores(data);
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar os professores disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const professorError: ProfessorProps = {
            professorId: "0",
            professorNome: error.message
          };
          setProfessores([professorError]);
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
      <InputLabel id="professor-select-label">Professor *</InputLabel>
      <Select
        labelId="professor-select-label"
        id="professor-select"
        value={value}
        label="Professor"
        onChange={onChange}
      >
        {professores.map((professor) => (
          <MenuItem key={professor.professorId} value={professor.professorId}>
            {professor.professorNome} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default ProfessorSelect;
