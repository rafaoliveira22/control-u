import config from "../config/config";
import { ProfessorProps } from "../interface/ProfessorProps";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()
export const registrarDadosProfessor = async (professor: ProfessorProps) => {
  const response = await fetch(`${config.apiUrl}/professor`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(professor),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar professor! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosProfessor = async (id: string) => {
  const response = await fetch(`${config.apiUrl}/professor/${id}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados do professor" + id + "! Tente novamente ou contate o suporte.");
  }

  return response.json();
};

export const obterTodosProfessores = async () => {
  const response = await fetch(`${config.apiUrl}/professor`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter dados dos professores! Tente novamente ou contate o suporte.");
  }

  return response.json();
};
