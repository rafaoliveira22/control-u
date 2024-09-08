import { NativeStackNavigationProp } from "@react-navigation/native-stack";

/**
 * Especifica que a propriedade navigation é do tipo NativeStackNavigationProp
 */
export type LoginScreenProps = {
  navigation: NativeStackNavigationProp<RootStackParamList, 'Login'>;
};

export type LeitorCarteirinhaScreenProps = {
  navigation: NativeStackNavigationProp<RootStackParamList, 'LeitorCarteirinha'>;
};