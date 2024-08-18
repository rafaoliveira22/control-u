type Cursos = {
  [key: number]: string; // Permite que números sejam usados como índices
};

const config = {
  apiUrl: 'http://localhost:8080/api',
  cursos: {
    1: 'Análise e Desenvolvimento de Sistemas',
    2: 'Ciência de Dados',
    3: 'Segurança da Informação',
    4: 'Gestão Comercial',
  } as Cursos
};

export default config;