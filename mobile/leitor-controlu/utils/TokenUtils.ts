import AsyncStorage from '@react-native-async-storage/async-storage';

const TOKEN_KEY = 'authToken';

// Método para salvar o token no AsyncStorage
export const salvarAuthToken = async (token: string) => {
  try {
    await AsyncStorage.setItem(TOKEN_KEY, token);
  } catch (error) {
    console.error("Erro ao salvar o token no AsyncStorage", error);
  }
};

// Método para obter o token do AsyncStorage
export const obterAuthToken = async (): Promise<string | null> => {
  try {
    return await AsyncStorage.getItem(TOKEN_KEY);
  } catch (error) {
    console.error("Erro ao obter o token do AsyncStorage", error);
    return null;
  }
};

// Método para remover o token do AsyncStorage (logout)
export const removerAuthToken = async () => {
  try {
    await AsyncStorage.removeItem(TOKEN_KEY);
  } catch (error) {
    console.error("Erro ao remover o token do AsyncStorage", error);
  }
};
