import config from "../config/config";
import { SalaProps } from "../interface/SalaProps";
import { fetchComToken } from "./HttpClientGeral";

export const registrarDadosSala = async  (sala: SalaProps) => {
  const response = await fetchComToken(`${config.apiUrl}/sala`, {
    method: 'POST',
    body: JSON.stringify(sala)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar sala! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterSala = async(id: string) =>{
  const response = await fetchComToken(`${config.apiUrl}/sala/${id}`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados da sala! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterTodasSalas = async () =>{
  const response = await fetchComToken(`${config.apiUrl}/sala`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados das salas registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}