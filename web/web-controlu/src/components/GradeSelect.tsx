import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { GradeProps } from '../interface/GradeProps';
import { obterDadosDeTodasGrades } from '../http/HttpClientGrade';

const GradeSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [grades, setGrades] = useState<GradeProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterDadosDeTodasGrades();
        if(isRelatorio){
          const dadosSelect = [{gradeId: 0, cursoId: 0, disciplinaId: 'Todos', professorId: '', cartaoId: ''}, ...data]
          setGrades(dadosSelect);
        } else{
          setGrades(data)
        }
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar as grades disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const erroPersonalizado: GradeProps = {
            gradeId: 0, cursoId: 0, disciplinaId: 'Erro ao obter as grades disponíveis.', professorId: '', cartaoId: ''
          };
          setGrades([erroPersonalizado]);
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
      <InputLabel id="select-label">Grade</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Aluno"
        onChange={onChange}
      >
        {grades.map((grade) => (
          <MenuItem key={grade.gradeId} value={grade.gradeId}>
            {`${grade.gradeId} - ${grade.disciplinaId}`} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default GradeSelect;
