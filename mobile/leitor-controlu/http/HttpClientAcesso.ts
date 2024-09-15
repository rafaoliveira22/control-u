import Config from "../config/Config";
import { AcessoProps } from "../props/AcessoProps";
import { obterAuthToken } from "../utils/TokenUtils";

let token: string | null = null;
obterAuthToken().then(tokenPromise => {token = tokenPromise});

export const registrarDadosAcesso = async  (acesso: AcessoProps) => {
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