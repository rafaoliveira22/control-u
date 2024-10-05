import config from "../config/config";
import { fetchComToken } from "./HttpClientGeral";

export const obterDadosTodosAcessos = async () => {
  const response = await fetchComToken(`${config.apiUrl}/acesso`, {
    method: 'GET',
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados dos acessos! Tente novamente ou contate o suporte.");
  }

  return response.json();
};