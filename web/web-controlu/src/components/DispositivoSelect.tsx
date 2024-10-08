import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { DispositivoProps } from '../interface/DispositivoProps';
import { obterDadosDeTodosDispositivos, obterDadosDeTodosDispositivosPorStatus } from '../http/HttpClientDispositivo';
import { SelectProps } from '../interface/SelectProps';

const DispositivoSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [dispositivos, setDispositivos] = useState<DispositivoProps[]>([]);

  const fetchDados = async () => {
    try {
      
      if(isRelatorio){
        const data = await obterDadosDeTodosDispositivos()
        const dadosSelect = [{dispositivoId: 'Todos', dispositivoStatus: ''}, ...data]
        setDispositivos(dadosSelect);
      } else{
        const data = await obterDadosDeTodosDispositivosPorStatus(2);
        setDispositivos(data)
      }
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao carregar os dispositivos disponíveis! Tente novamente ou contate o suporte."
        }
        toast.error(error.message);
        const dispositivoError: DispositivoProps = {
          dispositivoId: error.message,
          dispositivoStatus: 0,

        };
        setDispositivos([dispositivoError]);
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
      <InputLabel id="select-label">{isRelatorio ? 'Dispositivo de Leitura' : 'Dispositivo de Leitura *'}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Dispositivo"
        onChange={onChange}
      >
        {dispositivos.map((dispositivo) => (
          <MenuItem key={dispositivo.dispositivoId} value={dispositivo.dispositivoId}>
            {dispositivo.dispositivoId} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default DispositivoSelect;
