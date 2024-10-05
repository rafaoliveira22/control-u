import { obterAuthToken } from "../utils/TokenUtils";

// Função personalizada de fetch que adiciona o token automaticamente
export const fetchComToken = async (url: string, options: RequestInit = {}) => {
  const token = obterAuthToken();

  const headers = {
    ...options.headers,
    'Authorization': `Bearer ${token ? token : ''}`,
    'Content-Type': 'application/json',
  };

  const finalOptions = {
    ...options,
    headers: headers,
  };

  return fetch(url, finalOptions);
};