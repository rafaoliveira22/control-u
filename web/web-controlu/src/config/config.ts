type ObjetoComNumeroComoIndice = {
  [key: number]: string; // Permite que números sejam usados como índices
};


const config = {
  apiUrl: 'http://localhost:8080/api',
  cursos: {
    1: 'Análise e Desenvolvimento de Sistemas',
    2: 'Ciência de Dados',
    3: 'Segurança da Informação',
    4: 'Gestão Comercial',
    5: 'Desenvolvimento de Jogos',
  } as ObjetoComNumeroComoIndice,
  dispositivos: {
    tipos: {
      1: 'Catraca',
      2: 'Totem de Leitura',
    } as ObjetoComNumeroComoIndice, 
    status: {
      1: 'Online',
      2: 'Offline'
    } as ObjetoComNumeroComoIndice
  },
  cartao : {
    status: {
      1: 'Em uso',
      2: 'Sem uso'
    } as ObjetoComNumeroComoIndice
  },
  nivel_acesso: {
    1: 'ADM',
    2: 'COMUM',
    3: 'OPERADOR',
  } as ObjetoComNumeroComoIndice
};

export default config;