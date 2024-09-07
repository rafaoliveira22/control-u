import config from "../config/config";
import { CursoProps } from "../interface/CursoProps";
import { obterAuthToken } from "../utils/TokenUtils";

const token = obterAuthToken()
export const obterTodosCursos = async(): Promise<CursoProps[]> => {
  const response = await fetch(`${config.apiUrl}/curso`, {
    method: 'GET',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : 'Erro ao buscar cursos! Tente novamente ou contate o suporte.');
  }

  return response.json();
};

export const registrarDadosCurso = async (curso: CursoProps) => {
  const response = await fetch(`${config.apiUrl}/curso`, {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(curso),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar curso! Tente novamente ou contate o suporte.");
  }

  return response.json();
}