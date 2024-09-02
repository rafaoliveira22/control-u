import config from "../config/config";

export const obterDadosNivelDeAcesso = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/nivelacesso/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do nível de acesso! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosTodosNiveisDeAcesso = async () =>{
  const response = await fetch(`${config.apiUrl}/nivelacesso`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos níveis de acesso registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}