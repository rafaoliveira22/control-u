import config from "../config/config";
import { fetchComToken } from "./HttpClientGeral";

export const obterDadosNivelDeAcesso = async(id: string) =>{
  const response = await fetchComToken(`${config.apiUrl}/nivelacesso/${id}`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do nível de acesso! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosTodosNiveisDeAcesso = async () =>{
  const response = await fetchComToken(`${config.apiUrl}/nivelacesso`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos níveis de acesso registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}