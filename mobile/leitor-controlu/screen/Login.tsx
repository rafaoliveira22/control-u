import React, { useEffect, useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Alert, ActivityIndicator, Modal, Image } from 'react-native';
import { fazerLogin } from '../http/HttpClientUsuario';
import { obterAuthToken, removerAuthToken, salvarAuthToken } from '../utils/TokenUtils';
import { LoginScreenProps } from '../props/ScreenProps';
import ModalAlertLoading from '../components/ModalAlertLoading';

export const Login: React.FC<LoginScreenProps> = ({ navigation }) => {
  const [usuarioNome, setUsuarioNome] = useState<string>('')
  const [usuarioSenha, setUsuarioSenha] = useState<string>('')
  const [loading, setLoading] = useState<boolean>(false)

  const handleLogin = async () => {
    if(!usuarioNome || !usuarioSenha){
      setLoading(false)
      Alert.alert('Aviso', 'Por favor, preencha todos os campos.');
    } else{
      setLoading(true)
      const usuario = {
        usuarioNome: usuarioNome,
        usuarioSenha: usuarioSenha
      }

      try{
        const response = await fazerLogin(usuario)

        await salvarAuthToken(response.token)
        console.log(`Salvando token... ${response.token}`)
     
        setUsuarioNome('')
        setUsuarioSenha('')

        navigation.navigate('Menu')
      } catch(e){
        if(e instanceof Error) {
          if(e.message !== "Usuário inexistente ou senha inválida"){
             Alert.alert('Erro', 'Erro ao realizar login! Tente novamente ou contate o suporte.')
          } else{
            Alert.alert('Erro', e.message)
          }
        } else Alert.alert('Erro', 'Erro ao realizar login! Tente novamente ou contate o suporte.')
      } finally{
        setLoading(false)
      }
    }
  }

  return(
    <View style={styles.container}>
      <Image
        source={require('../assets/logo.png')} 
        style={styles.logo}
        alt="Logo ControlU"
      />
      <TextInput
        style={styles.input}
        placeholder="Usuário"
        value={usuarioNome}
        onChangeText={setUsuarioNome}
        autoCapitalize='none'
      />
      <TextInput
        style={styles.input}
        placeholder="Senha"
        secureTextEntry
        value={usuarioSenha}
        onChangeText={setUsuarioSenha}
      />
      <TouchableOpacity style={styles.button} onPress={handleLogin}>
        <Text style={styles.buttonText}>Entrar</Text>
      </TouchableOpacity>

      {/* Modal com loader */}
      {loading && (
        <ModalAlertLoading loading={loading} texto='Entrando...' />
      )}
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    paddingHorizontal: 16,
    backgroundColor: '#f5f5f5',
  },
  logo: {
    textAlign: 'center',
    marginBottom: 24,
    resizeMode: 'contain',
    width: '100%'
  },
  input: {
    height: 50,
    borderColor: '#ccc',
    borderWidth: 1,
    marginBottom: 12,
    paddingHorizontal: 10,
    backgroundColor: '#fff',
    borderRadius: 8,
  },  
  button: {
    backgroundColor: '#115571',
    paddingVertical: 12,
    paddingHorizontal: 20,
    borderRadius: 8,
  },
  buttonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  modalBackground: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  activityIndicatorWrapper: {
    backgroundColor: '#fff',
    height: 100,
    width: 150,
    borderRadius: 10,
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  loadingText: {
    marginTop: 10,
    fontSize: 16,
    fontWeight: 'bold',
  },
});