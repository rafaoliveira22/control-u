export interface DispositivoSelectProps {
  value: number | string;
  onChange: (event: React.ChangeEvent<{ value: unknown }>) => void;
}

export interface DispositivoProps {
  dispositivoId: string;
  dispositivoStatus: number
}

export interface DispositivoCadastroProps {
  dispositivoTipo: number
}