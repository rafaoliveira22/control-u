import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import CursoSelect from './CursoSelect';
import { DispositivoProps, DispositivoSelectProps } from '../interface/DispositivoProps';
import { obterDadosDeTodosDispositivos } from '../http/HttpClientDispositivo';

const DispositivoSelect: React.FC<DispositivoSelectProps> = ({ value, onChange }) => {
  const [dispositivos, setDispositivos] = useState<DispositivoProps[]>([]);

  const fetchDados = async () => {
    try {
      const data = await obterDadosDeTodosDispositivos();
      setDispositivos(data);
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
    <FormControl fullWidth>
      <Toaster richColors  expand={true} />
      <InputLabel id="select-label">Dispositivos *</InputLabel>
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

export default CursoSelect;
