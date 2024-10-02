import React, { useState } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { SelectProps } from '../interface/SelectProps';
import { TipoRelatorio } from '../interface/RelatorioProps';
import { Toaster } from 'sonner';

const TipoRelatorioSelect: React.FC<SelectProps> = ({ value, onChange }) => {
  const [tiposRelatorio, setTipoRelatorio] = useState<TipoRelatorio[]>([
    {tipo: 'ACESSO'},
    {tipo: 'AULA'},
    {tipo: 'PRESENÇA'},
  ]);

  return (
    <FormControl fullWidth sx={{ mb: 2 }}>
      <Toaster richColors  expand={true} />
      <InputLabel id="select-label">Tipo Relatório *</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Tipo Relatório"
        onChange={onChange}
      >
        {tiposRelatorio.map((tipo) => (
          <MenuItem key={tipo.tipo} value={tipo.tipo}>
            {tipo.tipo} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default TipoRelatorioSelect;
