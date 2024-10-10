import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { obterTodosCursos } from '../http/HttpClientCurso';
import { Toaster, toast } from 'sonner';
import { CursoProps } from '../interface/CursoProps';
import { SelectProps } from '../interface/SelectProps';

const CursoSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [cursos, setCursos] = useState<CursoProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterTodosCursos();
        setCursos(data);
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar os cursos disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const cursoError: CursoProps = {
            cursoId: 0,
            cursoNome: error.message
          };
          setCursos([cursoError]);
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
      <InputLabel id="select-label">{isRelatorio ? 'Curso' : 'Curso *'}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Curso"
        onChange={onChange}
      >
        {cursos.map((curso) => (
          <MenuItem key={curso.cursoId} value={curso.cursoId}>
            {curso.cursoNome} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default CursoSelect;
