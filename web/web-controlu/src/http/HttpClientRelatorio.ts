import config from "../config/config";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()

interface FiltroRelatorioVO {
  dataInicial: string;
  dataFinal: string;
  alunoId?: string;
  aulaId?: number;
}

export const gerarRelatorio = async (filtroRelatorio: FiltroRelatorioVO) => {
  console.log(filtroRelatorio)

  const response = await fetch(`${config.apiUrl}/relatorio`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(filtroRelatorio)
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados do relatório! Tente novamente ou contate o suporte.");
  }

  return response.blob();
};