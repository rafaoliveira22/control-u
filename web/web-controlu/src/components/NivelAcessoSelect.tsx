import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { NivelAcessoProps } from '../interface/NivelAcessoProps';
import { obterDadosTodosNiveisDeAcesso } from '../http/HttpClientNivelAcesso';

const NivelAcessoSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [niveisDeAcesso, setNiveisDeAcesso] = useState<NivelAcessoProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterDadosTodosNiveisDeAcesso();
        setNiveisDeAcesso(data);
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar os níveis de acesso disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const nivelAcessoError: NivelAcessoProps = {
            nivelAcessoId: 0,
            nivelAcessoNome: error.message
          };
          setNiveisDeAcesso([nivelAcessoError]);
        } else {
          toast.error('Erro desconhecido');
        }
      }
    };

    fetchDados();
  }, []);

  return (
    <FormControl fullWidth>
      <Toaster richColors  expand={true} />
      <InputLabel id="select-label">{isRelatorio ? 'Nível de Acesso' : 'Nível de Acesso *'}</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label={isRelatorio ? 'Nível de Acesso' : 'Nível de Acesso *'}
        onChange={onChange}
      >
        {niveisDeAcesso.map((nivelAcesso) => (
          <MenuItem key={nivelAcesso.nivelAcessoId} value={nivelAcesso.nivelAcessoId}>
            {nivelAcesso.nivelAcessoNome} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default NivelAcessoSelect;
