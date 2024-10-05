import config from "../config/config";
import { FiltroRelatorioAcesso, FiltroRelatorioAula, FiltroRelatorioPresenca } from "../interface/RelatorioProps";
import { fetchComToken } from "./HttpClientGeral";


export const gerarRelatorio = async (filtroRelatorio: (FiltroRelatorioPresenca | FiltroRelatorioAcesso | FiltroRelatorioAula | null)) => {
  const response = await fetchComToken(`${config.apiUrl}/relatorio`, {
    method: 'POST',
    body: JSON.stringify(filtroRelatorio),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados do relatório! Tente novamente ou contate o suporte.");
  }

  return response.blob();
};