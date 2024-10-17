import config from "../config/config";
import { AlunoProps } from "../interface/AlunoProps";
import { fetchComToken } from "./HttpClientGeral";

export const registrarDadosAluno = async (aluno: AlunoProps) => {
  console.log(aluno)
  const response = await fetchComToken(`${config.apiUrl}/aluno`, {
    method: 'POST',
    body: JSON.stringify(aluno),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar aluno! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosAluno = async (id: string) => {
  const response = await fetchComToken(`${config.apiUrl}/aluno/${id}`, {
    method: 'GET',
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do professor! Tente novamente ou contate o suporte.");
  }

  return response.json();
};

export const obterDadosDeTodosAlunos = async () => {
  const response = await fetchComToken(`${config.apiUrl}/aluno`, {
    method: 'GET',
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados dos alunos! Tente novamente ou contate o suporte.");
  }

  return response.json();
};

export const atualizarDadosAluno = async (aluno: AlunoProps) => {
  const response = await fetchComToken(`${config.apiUrl}/aluno`, {
    method: 'PUT',
    body: JSON.stringify(aluno),
  });

  if (!response.ok) {
    throw new Error('Error');
  }

  return response.json();
};

export const apagarDadosAlunos = async (id: string) => {
  const response = await fetchComToken(`${config.apiUrl}/aluno/${id}`, {
    method: 'DELETE',
  });

  if (!response.ok) {
    throw new Error('Error');
  }

  return response.json();
};