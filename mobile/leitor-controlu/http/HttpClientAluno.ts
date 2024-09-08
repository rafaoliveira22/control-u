import Config from "../config/Config"
import { obterAuthToken } from "../utils/TokenUtils"

const token = obterAuthToken()
export const verificarSeEstaRegistrado = async(ra: string) => {
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