import config from "../config/config";
import { GradeCadastroProps } from "../interface/GradeProps";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()
export const registrarDadosGrade = async  (grade: GradeCadastroProps) => {
  const response = await fetch(`${config.apiUrl}/grade`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(grade)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar grade! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosGrade = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/grade/${id}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados da grade! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDeTodasGrades = async () =>{
  const response = await fetch(`${config.apiUrl}/grade`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados das grades registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}