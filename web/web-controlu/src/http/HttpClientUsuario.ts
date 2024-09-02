import config from "../config/config";
import { UsuarioCadastroProps } from "../interface/UsuarioProps";

export const registrarDadosUsuario = async  (usuario: UsuarioCadastroProps) => {
  const response = await fetch(`${config.apiUrl}/usuario`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(usuario)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar usuário! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosUsuario = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/usuario/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do usuário! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosTodosUsuarios = async () =>{
  const response = await fetch(`${config.apiUrl}/usuario`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos usuários registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}