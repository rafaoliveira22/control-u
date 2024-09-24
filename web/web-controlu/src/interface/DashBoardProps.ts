export interface DashboardProps {
  titulo: string, 
  subtitulo: string,
  quantidade: number,
  quantidadeOposto: number
}

export interface DashboardRegistrosRecentesProps {
  dados: {
    data: string;
    horario: string;
    descricao: string;
  }[];
}