import Config from "../config/Config";
import { AulaProps } from "../props/AulaProps";
import { PresencaProps } from "../props/PresencaProps";
import { obterAuthToken } from "../utils/TokenUtils";


let token: string | null = null;
export const registrarDadosPresenca = async  (presenca: PresencaProps) => {
    // Garantir que o token seja obtido antes de fazer a requisição
    token = await obterAuthToken();

    if (!token) {
      throw new Error('Token de autenticação não encontrado');
    }
  
  const response = await fetch(`${Config.apiUrl}/presenca`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(presenca)
  })
  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message);
  }

  return response.json()
}