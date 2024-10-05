import config from "../config/config";
import { ProfessorProps } from "../interface/ProfessorProps";
import { fetchComToken } from "./HttpClientGeral";

export const registrarDadosProfessor = async (professor: ProfessorProps) => {
  const response = await fetchComToken(`${config.apiUrl}/professor`, {
    method: 'POST',
    body: JSON.stringify(professor),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar professor! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosProfessor = async (id: string) => {
  const response = await fetchComToken(`${config.apiUrl}/professor/${id}`, {
    method: 'GET',
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados do professor" + id + "! Tente novamente ou contate o suporte.");
  }

  return response.json();
};

export const obterTodosProfessores = async () => {
  const response = await fetchComToken(`${config.apiUrl}/professor`, {
    method: 'GET',
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados dos professores! Tente novamente ou contate o suporte.");
  }

  return response.json();
};
