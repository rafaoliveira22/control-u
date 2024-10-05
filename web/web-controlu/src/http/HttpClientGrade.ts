import config from "../config/config";
import { GradeCadastroProps } from "../interface/GradeProps";
import { fetchComToken } from "./HttpClientGeral";

export const registrarDadosGrade = async  (grade: GradeCadastroProps) => {
  const response = await fetchComToken(`${config.apiUrl}/grade`, {
    method: 'POST',
    body: JSON.stringify(grade)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar grade! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosGrade = async(id: string) =>{
  const response = await fetchComToken(`${config.apiUrl}/grade/${id}`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados da grade! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDeTodasGrades = async () =>{
  const response = await fetchComToken(`${config.apiUrl}/grade`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados das grades registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}