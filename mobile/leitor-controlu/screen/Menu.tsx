import React from 'react'
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native';
import { MenuScreenProps } from '../props/ScreenProps';

export const Menu: React.FC<MenuScreenProps> = ({ navigation }) => {
  const handleClick = (screen: keyof RootStackParamList) => {
    navigation.navigate(screen)
  }


  return(
    <View style={styles.container}> 
      <TouchableOpacity style={styles.button} onPress={() => {handleClick('LeitorCarteirinhaAcesso')}}>
        <Text style={styles.buttonText}>Controle de Acesso</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.button} onPress={() => {navigation.navigate('LeitorAula')}}>
        <Text style={styles.buttonText}>Controle Presenças na Aula</Text>
      </TouchableOpacity>
    </View>
  )
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    gap: 10
  },  
  button: {
    backgroundColor: '#115571',
    paddingVertical: 12,
    paddingHorizontal: 20,
    borderRadius: 8,
    width: '80%'
  },
  buttonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
  },
});