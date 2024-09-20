import config from "../config/config";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()

export const obterDadosAlunosAula = async () => {
  const response = await fetch(`${config.apiUrl}/dashboard/aluno/aula`, {
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

export const obterDadosAlunosAcesso = async () => {
  const response = await fetch(`${config.apiUrl}/dashboard/aluno/acesso`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    throw new Error("Erro ao obter dados dos alunos na faculdade! Tente novamente ou contate o suporte.");
  }

  return response.json();
};

export const obterDadosAula = async () => {
  const response = await fetch(`${config.apiUrl}/dashboard/aula`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    throw new Error("Erro ao obter dados das aulas acontecendo! Tente novamente ou contate o suporte.");
  }

  return response.json();
};