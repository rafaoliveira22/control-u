import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { PresencaProps } from '../interface/PresencaProps';
import { obterDadosTodasPresencas } from '../http/HttpClientPresenca';

const PresencaSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [presencas, setPresencas] = useState<PresencaProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterDadosTodasPresencas();
        if(isRelatorio){
          const dadosSelect = [{presencaId: 0, presencaEntrada: 'Todos', presencaSaida: '', alunoId: '', aulaId: ''}, ...data]
          setPresencas(dadosSelect)
        } else{
          setPresencas(data);
        }
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar as presenças disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const erroPersonalizado: PresencaProps = {
            presencaId: 0, presencaEntrada: 'Erro ao obter as presenças.', presencaSaida: '', alunoId: '', aulaId: 0
          };
          setPresencas([erroPersonalizado]);
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
      <InputLabel id="select-label">{isRelatorio ? 'Presença ID' : 'Presença ID *'}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label={isRelatorio ? 'Presença ID' : 'Presença ID *'}
        onChange={onChange}
      >
        {presencas.map((presenca) => (
          <MenuItem key={presenca.presencaId} value={presenca.presencaId}>
            {`${presenca.presencaId} - ${presenca.presencaEntrada}`} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default PresencaSelect;
