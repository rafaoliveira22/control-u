import Config from "../config/Config";
import { AulaProps } from "../props/AulaProps";
import { obterAuthToken } from "../utils/TokenUtils";

let token: string | null = null;
obterAuthToken().then(tokenPromise => {token = tokenPromise});

export const registrarDadosAula = async  (aula: AulaProps) => {
  const response = await fetch(`${Config.apiUrl}/aula`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(aula)
  })
  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message);
  }

  return response.json()
}