import config from "../config/config";
import { SalaProps } from "../interface/SalaProps";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()
export const registrarDadosSala = async  (sala: SalaProps) => {
  const response = await fetch(`${config.apiUrl}/sala`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(sala)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar sala! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterSala = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/sala/${id}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados da sala! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterTodasSalas = async () =>{
  const response = await fetch(`${config.apiUrl}/sala`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados das salas registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}