import config from "../config/config";
import { AlunoProps } from "../interface/AlunoProps";

export const postAluno = async (aluno: AlunoProps) => {
  const response = await fetch(`${config.apiUrl}/aluno`, {
    method: 'POST',
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

export const getAlunoByRa = async (id: string) => {
  const response = await fetch(`${config.apiUrl}/aluno/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    throw new Error('Error');
  }

  return response.json();
};

export const getAlunos = async () => {
  const response = await fetch(`${config.apiUrl}/aluno`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  });

  if (!response.ok) {
    throw new Error('Error');
  }

  return response.json();
};