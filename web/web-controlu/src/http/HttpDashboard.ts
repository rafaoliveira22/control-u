import config from "../config/config";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()

export const obterDadosDashboard = async () => {
  const response = await fetch(`${config.apiUrl}/dashboard`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    throw new Error("Erro ao obter dados dos alunos em aula! Tente novamente ou contate o suporte.");
  }

  return response.json();
};