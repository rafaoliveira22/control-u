import Config from "../config/Config"
import { UsuarioLoginProps } from "../props/UsuarioProps"

export const fazerLogin = async  (usuario: UsuarioLoginProps) => {
  const response = await fetch(`${Config.apiUrl}/auth/login`, {
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