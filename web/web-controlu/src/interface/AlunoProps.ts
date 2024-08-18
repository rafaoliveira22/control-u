export interface AlunoIdProps {
  alunoNome: string;
  cursoId: number;
  alunoAnoIngressao: number;
}

export interface AlunoProps {
  alunoRa: string;
  id: AlunoIdProps;
}