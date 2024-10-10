import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { AlunoProps } from '../interface/AlunoProps';
import { obterDadosDeTodosAlunos } from '../http/HttpClientAluno';

const AlunoSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [alunos, setAlunos] = useState<AlunoProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterDadosDeTodosAlunos();
        if(isRelatorio){
          const dadosAlunosSelect = [{alunoRa: '0', alunoNome: 'Todos', cursoId: 0}, ...data]
          setAlunos(dadosAlunosSelect);
        } else{
          setAlunos(data)
        }
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar os alunos disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const alunoError: AlunoProps = {
            alunoRa: "0",
            alunoNome: "Erro ao carregar os alunos disponíveis",
            cursoId: 0
          };
          setAlunos([alunoError]);
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
      <InputLabel id="select-label">{isRelatorio ? 'Aluno' : 'Aluno *'}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Aluno"
        onChange={onChange}
      >
        {alunos.map((aluno) => (
          <MenuItem key={aluno.alunoRa} value={aluno.alunoRa}>
            {`${aluno.alunoRa} - ${aluno.alunoNome}`} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default AlunoSelect;
