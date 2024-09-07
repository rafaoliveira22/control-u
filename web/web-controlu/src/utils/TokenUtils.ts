const TOKEN_KEY = 'authToken';

// Método para salvar o token no localStorage
export const salvarAuthToken = (token: string) => {
  localStorage.setItem(TOKEN_KEY, token);
};

// Método para obter o token do localStorage
export const obterAuthToken = (): string | null => {
  return localStorage.getItem(TOKEN_KEY);
};


// Método para remover o token do localStorage (logout)
export const removerAuthToken = () => {
  localStorage.removeItem(TOKEN_KEY);
};
