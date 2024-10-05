import config from "../config/config";
import { UsuarioCadastroProps, UsuarioLoginProps } from "../interface/UsuarioProps";
import { fetchComToken } from "./HttpClientGeral";

export const fazerLogin = async  (usuario: UsuarioLoginProps) => {
  const response = await fetch(`${config.apiUrl}/auth/login`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(usuario)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message);
  }

  return response.json()
}

export const registrarDadosUsuario = async  (usuario: UsuarioCadastroProps) => {
  const response = await fetchComToken(`${config.apiUrl}/usuario`, {
    method: 'POST',
    body: JSON.stringify(usuario)
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar usuário! Tente novamente ou contate o suporte.");
  }

  return response.json();
}


export const obterDadosUsuario = async(id: string) =>{
  const response = await fetchComToken(`${config.apiUrl}/usuario/${id}`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do usuário! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosTodosUsuarios = async () =>{
  const response = await fetchComToken(`${config.apiUrl}/usuario`, {
    method: 'GET',
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos usuários registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}