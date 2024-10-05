import config from "../config/config";
import { CursoProps } from "../interface/CursoProps";
import { fetchComToken } from "./HttpClientGeral";

export const obterTodosCursos = async(): Promise<CursoProps[]> => {
  const response = await fetchComToken(`${config.apiUrl}/curso`, {
    method: 'GET',
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : 'Erro ao buscar cursos! Tente novamente ou contate o suporte.');
  }

  return response.json();
};

export const registrarDadosCurso = async (curso: CursoProps) => {
  const response = await fetchComToken(`${config.apiUrl}/curso`, {
    method: 'POST',
    body: JSON.stringify(curso),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : "Erro ao cadastrar curso! Tente novamente ou contate o suporte.");
  }

  return response.json();
}