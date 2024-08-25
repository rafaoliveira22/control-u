export interface CursoSelectProps {
  value: number | string;
  onChange: (event: React.ChangeEvent<{ value: unknown }>) => void;
}

export interface CursoProps {
  cursoId: number;
  cursoNome: string; 
}

