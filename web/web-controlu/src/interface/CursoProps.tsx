export interface CursoSelectProps {
  value: number | string;
  onChange: (event: React.ChangeEvent<{ value: unknown }>) => void;
}

export interface Curso {
  cursoId: number;
  cursoNome: string; 
}

