import config from "../config/config";
import { DisciplinaProps } from "../interface/DisciplinaProps";

export const registrarDisciplina = async  (disciplina: DisciplinaProps) => {
  const response = await fetch(`${config.apiUrl}/disciplina`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(disciplina)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar disciplina! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDisciplina = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/disciplina/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados da disciplina! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDeTodasDisciplinas = async () =>{
  const response = await fetch(`${config.apiUrl}/disciplina`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados das disciplinas registradas! Tente novamente ou contate o suporte.");
  }

  return response.json();
}