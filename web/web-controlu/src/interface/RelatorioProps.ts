export interface FiltroRelatorioPresenca{
  tipo: string
  dataInicial: string | null;
  dataFinal: string | null;
  alunoId?: string | null;

  presencaId?: number | null;
  aulaId?: string | null;
  gradeId?: string | null;
}

export interface FiltroRelatorioAcesso{
  tipo: string
  dataInicial: string | null;
  dataFinal: string | null;
  alunoId?: string | null;
  acessoId?: number | null;
  dispositivoId?: string | null;
}

export interface FiltroRelatorioAula{
  tipo: string
  dataInicial: string | null;
  dataFinal: string | null;

  aulaId?: string | null;
  gradeId?: string | null;
  salaId?: string | null;
  professorId?: string | null;
}

export interface TipoRelatorio{
  tipo: string;
}