import config from "../config/config";
import { AlunoProps } from "../interface/AlunoProps";

export const registrarDadosAluno = async (aluno: AlunoProps) => {
  const response = await fetch(`${config.apiUrl}/aluno`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(aluno),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar aluno! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosAluno = async (id: string) => {
  const response = await fetch(`${config.apiUrl}/aluno/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do professor! Tente novamente ou contate o suporte.");
  }

  return response.json();
};

export const obterDadosDeTodosAlunos = async () => {
  const response = await fetch(`${config.apiUrl}/aluno`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados dos alunos! Tente novamente ou contate o suporte.");
  }

  return response.json();
};

export const atualizarDadosAluno = async (aluno: AlunoProps) => {
  const response = await fetch(`${config.apiUrl}/aluno`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(aluno),
  });

  if (!response.ok) {
    throw new Error('Error');
  }

  return response.json();
};

export const apagarDadosAlunos = async (id: string) => {
  const response = await fetch(`${config.apiUrl}/aluno/${id}`, {
    method: 'DELETE',
    headers: {
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    throw new Error('Error');
  }

  return response.json();
};