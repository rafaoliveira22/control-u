import React, { useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, StyleSheet, Alert, ActivityIndicator, Modal } from 'react-native';
import { fazerLogin } from '../http/HttpClientUsuario';
import { obterAuthToken, removerAuthToken } from '../utils/TokenUtils';
import { LoginScreenProps } from '../props/ScreenProps';

export const Login: React.FC<LoginScreenProps> = ({ navigation }) => {
  const [usuarioNome, setUsuarioNome] = useState<string>('')
  const [usuarioSenha, setUsuarioSenha] = useState<string>('')
  const [loading, setLoading] = useState<boolean>(false)

  const handleLogin = async () => {
    if(!usuarioNome || !usuarioSenha){
      setLoading(false)
      Alert.alert('Erro', 'Por favor, preencha todos os campos.');
    } else{
      const usuario = {
        usuarioNome: usuarioNome,
        usuarioSenha: usuarioSenha
      }

      try{
        const response = await fazerLogin(usuario)
        await removerAuthToken()
        await obterAuthToken()
        
        setUsuarioNome('')
        setUsuarioSenha('')

        navigation.navigate('LeitorCarteirinha')
      } catch(e){
        if(e instanceof Error) {
          if(e.message.toLowerCase().includes('network')){
            e.message = 'Erro de conexão! Tente novamente ou contaate o suporte.'
          }
          Alert.alert('Erro', e.message)
        } else Alert.alert('Erro', 'Erro ao realizar login! Tente novamente ou contaate o suporte.')
      } finally{
        setLoading(false)
      }
    }
  }

  return(
    <View style={styles.container}>
      <Text style={styles.title}>ControlU Login</Text>
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
        <Modal
          transparent={true}
          animationType="none"
          visible={loading}
          onRequestClose={() => {}}
        >
          <View style={styles.modalBackground}>
            <View style={styles.activityIndicatorWrapper}>
              <ActivityIndicator size="large" color="#0000ff" />
              <Text style={styles.loadingText}>Entrando...</Text>
            </View>
          </View>
        </Modal>
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
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 24,
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
    backgroundColor: '#1E90FF',
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