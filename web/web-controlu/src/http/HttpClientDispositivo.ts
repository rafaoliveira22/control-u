import config from "../config/config";
import { DispositivoCadastroProps } from "../interface/DispositivoProps";

export const registrarDispositivo = async  (dispositivo: DispositivoCadastroProps) => {
  const response = await fetch(`${config.apiUrl}/dispositivo`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(dispositivo)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar dispositivo! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDispositivo = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/dispositivo/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do dispositivo! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosDeTodosDispositivos = async () =>{
  const response = await fetch(`${config.apiUrl}/dispositivo`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos dispositivos registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}