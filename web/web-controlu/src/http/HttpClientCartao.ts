import config from "../config/config";

export const registrarDadosCartao = async  () => {
  const response = await fetch(`${config.apiUrl}/cartao`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar cartão de leitura! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosCartao = async(id: string) =>{
  const response = await fetch(`${config.apiUrl}/cartao/${id}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados do cartão de leitura! Tente novamente ou contate o suporte.");
  }

  return response.json();
}

export const obterDadosTodosCartoes = async () =>{
  const response = await fetch(`${config.apiUrl}/cartao`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    }
  })

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao obter os dados dos cartões de leitura registrados! Tente novamente ou contate o suporte.");
  }

  return response.json();
}