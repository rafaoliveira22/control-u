import Config from "../config/Config";
import { AcessoProps } from "../props/AcessoProps";
import { obterAuthToken } from "../utils/TokenUtils";



let token: string | null = null;
export const registrarDadosAcesso = async  (acesso: AcessoProps) => {
  // Garantir que o token seja obtido antes de fazer a requisição
  token = await obterAuthToken();

  if (!token) {
    throw new Error('Token de autenticação não encontrado');
  }
  
  const response = await fetch(`${Config.apiUrl}/acesso`, {
    method: 'POST', 
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(acesso)
  })
  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message);
  }

  return response.json()
}