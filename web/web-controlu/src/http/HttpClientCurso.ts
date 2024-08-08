import config from "../config/config";
import { Curso } from "../interface/CursoProps";

export const buscarTodosCursos = async(): Promise<Curso[]> => {
  const response = await fetch(`${config.apiUrl}/curso`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message ? errorData.message : 'Erro ao buscar cursos! Tente novamente ou contate o suporte.');
  }

  return response.json();
};