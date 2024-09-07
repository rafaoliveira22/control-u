import config from "../config/config";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()
export const registrarDadosCartao = async  () => {
  const response = await fetch(`${config.apiUrl}/cartao`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar cartão de leitura! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosCartao = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/cartao/${id}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do cartão de leitura! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosTodosCartoes = async () =>{
  const response = await fetch(`${config.apiUrl}/cartao`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos cartões de leitura registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosTodosCartoesQueNaoEstaoAssociadosAUmaGrade = async () =>{
  const response = await fetch(`${config.apiUrl}/cartao/all/not/in/grade`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos cartões de leitura registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}