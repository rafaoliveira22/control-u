import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { AcessoProps } from '../interface/AcessoProps';
import { obterDadosTodosAcessos } from '../http/HttpClientAcesso';

const AcessoSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [acessos, setAcessos] = useState<AcessoProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterDadosTodosAcessos();
        if(isRelatorio){
          const dadosSelect = [{acessoId: 0, acessoEntrada: 'Todos', acessoSaida: '', dispositivoId: '', alunoId: ''}, ...data]
          setAcessos(dadosSelect);
        } else{
          setAcessos(data);
        }
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar os acessos disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const erroPersonalizado: AcessoProps = {
            acessoId: 0, acessoEntrada: 'Erro ao obter os acessos', acessoSaida: '', dispositivoId: '', alunoId: ''
          };
          setAcessos([erroPersonalizado]);
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
      <InputLabel id="select-label">Acesso</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Sala"
        onChange={onChange}
      >
        {acessos.map((acesso) => (
          <MenuItem key={acesso.acessoId} value={acesso.acessoId}>
            {`${acesso.acessoId} - ${acesso.acessoEntrada}`} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default AcessoSelect;
