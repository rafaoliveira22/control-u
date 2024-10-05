import config from "../config/config";
import { fetchComToken } from "./HttpClientGeral";

export const obterDadosDashboard = async () => {
  const response = await fetchComToken(`${config.apiUrl}/dashboard`, {
    method: 'GET',
  });

  if (!response.ok) {
    throw new Error("Erro ao obter dados dos alunos em aula! Tente novamente ou contate o suporte.");
  }

  return response.json();
};