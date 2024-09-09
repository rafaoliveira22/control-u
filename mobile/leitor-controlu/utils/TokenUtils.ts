import AsyncStorage from '@react-native-async-storage/async-storage';

const TOKEN_KEY = 'authToken';

// Método para salvar o token no AsyncStorage
export const salvarAuthToken = async (token: string) => {
  await AsyncStorage.setItem(TOKEN_KEY, token);
};

// Método para obter o token do AsyncStorage
export const obterAuthToken = async () => {
  return await AsyncStorage.getItem(TOKEN_KEY);
};

// Método para remover o token do AsyncStorage (logout)
export const removerAuthToken = async () => {
  await AsyncStorage.removeItem(TOKEN_KEY);
};
