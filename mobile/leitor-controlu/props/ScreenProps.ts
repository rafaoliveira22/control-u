import { NativeStackNavigationProp } from "@react-navigation/native-stack";

/**
 * Especifica que a propriedade navigation é do tipo NativeStackNavigationProp
 */
export type LoginScreenProps = {
  navigation: NativeStackNavigationProp<RootStackParamList, 'Login'>;
};

export type LeitorCarteirinhaAcessoScreenProps = {
  navigation: NativeStackNavigationProp<RootStackParamList, 'LeitorCarteirinhaAcesso'>;
  alunoId?: string
};

export type LeitorAulaScreenProps = {
  navigation: NativeStackNavigationProp<RootStackParamList, 'LeitorAula'>;
};

export type MenuScreenProps = {
  navigation: NativeStackNavigationProp<RootStackParamList, 'Menu'>;
};

export type LeitorReconhecimentoFacialScreenProps = {
  navigation: NativeStackNavigationProp<RootStackParamList, 'LeitorReconhecimentoFacial'>;
  route: {
    params: {
      alunoId: string;
    };
  };
};