import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { buscarTodosCursos } from '../http/HttpClientCurso';
import { Toaster, toast } from 'sonner';
import { Curso, CursoSelectProps } from '../interface/CursoProps';

const CursoSelect: React.FC<CursoSelectProps> = ({ value, onChange }) => {
  const [cursos, setCursos] = useState<Curso[]>([]);

  useEffect(() => {
    const fetchCursos = async () => {
      try {
        const data = await buscarTodosCursos();
        setCursos(data);
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar os cursos disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const cursoError: Curso = {
            cursoId: 0,
            cursoNome: error.message
          };
          setCursos([cursoError]);
        } else {
          toast.error('Erro desconhecido');
        }
      }
    };

    fetchCursos();
  }, []);

  return (
    <FormControl fullWidth>
      <Toaster richColors  expand={true} />
      <InputLabel id="curso-select-label">Cursos</InputLabel>
      <Select
        labelId="curso-select-label"
        id="curso-select"
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
