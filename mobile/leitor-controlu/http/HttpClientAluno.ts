import Config from "../config/Config"
import { obterAuthToken } from "../utils/TokenUtils"

let token: string | null = null;
export const verificarSeEstaRegistrado = async(ra: string) => {
  // Garantir que o token seja obtido antes de fazer a requisição
  token = await obterAuthToken();

  if (!token) {
    throw new Error('Token de autenticação não encontrado');
  }

  const response = await fetch(`${Config.apiUrl}/aluno/verificarSeEstaRegistrado/${ra}`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message);
  }

  return response.json();
}