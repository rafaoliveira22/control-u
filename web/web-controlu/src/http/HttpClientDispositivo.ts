import config from "../config/config";
import { DispositivoCadastroProps } from "../interface/DispositivoProps";
import { fetchComToken } from "./HttpClientGeral";

export const registrarDispositivo = async  (dispositivo: DispositivoCadastroProps) => {
  const response = await fetchComToken(`${config.apiUrl}/dispositivo`, {
    method: 'POST',
    body: JSON.stringify(dispositivo)
  })

  if (!response.ok) {    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar dispositivo! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDispositivo = async(id: string) =>{
  const response = await fetchComToken(`${config.apiUrl}/dispositivo/${id}`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do dispositivo! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDeTodosDispositivos = async () =>{
  const response = await fetchComToken(`${config.apiUrl}/dispositivo`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos dispositivos registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDeTodosDispositivosPorStatus = async (status : number) =>{
  const response = await fetchComToken(`${config.apiUrl}/dispositivo/status/${status}`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos dispositivos registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}