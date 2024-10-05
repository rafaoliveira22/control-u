import config from "../config/config";
import { fetchComToken } from "./HttpClientGeral";

export const obterDadosTodasPresencas = async () => {
  const response = await fetchComToken(`${config.apiUrl}/presenca`, {
    method: 'GET',
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados das presenças! Tente novamente ou contate o suporte.");
  }

  return response.json();
};