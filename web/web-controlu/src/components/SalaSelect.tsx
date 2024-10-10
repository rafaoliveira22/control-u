import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { SalaProps } from '../interface/SalaProps';
import { obterTodasSalas } from '../http/HttpClientSala';

const SalaSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [salas, setSalas] = useState<SalaProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterTodasSalas();
        if(isRelatorio){
          const dadosSelect = [{salaId: '0', salaNome: 'Todos', dispositivoId: '0'}, ...data]
          setSalas(dadosSelect);
        } else{
          setSalas(data);
        }
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar as salas disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const erroPersonalizado: SalaProps = {
            salaId: '0', salaNome: 'Erro ao carregar as salass disponíveis', dispositivoId: '0'
          };
          setSalas([erroPersonalizado]);
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
      <InputLabel id="select-label">{isRelatorio ? 'Sala (Ambiente)' : 'Sala (Ambiente) *'}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label={isRelatorio ? 'Sala (Ambiente)' : 'Sala (Ambiente) *'}
        onChange={onChange}
      >
        {salas.map((sala) => (
          <MenuItem key={sala.salaId} value={sala.salaId}>
            {`${sala.salaId} - ${sala.salaNome}`} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default SalaSelect;
