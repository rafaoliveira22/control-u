import React, { useState, useEffect } from 'react';
import { Text, View, Button, StyleSheet, TouchableOpacity, Alert, Image } from 'react-native';
import { CameraView, useCameraPermissions, Camera } from 'expo-camera';
import { BarcodeScanningResult, CameraType } from 'expo-camera/build/Camera.types';
import { LeitorReconhecimentoFacialScreenProps } from '../props/ScreenProps';
import { LeitorCarteirinha } from '../components/LeitorCarteirinha';
import Config from '../config/Config';
import { registrarDadosAula } from '../http/HttClientAula';
import { registrarDadosPresenca } from '../http/HttpClientPresenca';
import { AcessoCadastroProps, AcessoProps } from '../props/AcessoProps';
import { registrarDadosAcesso } from '../http/HttpClientAcesso';
import ModalAlertLoading from '../components/ModalAlertLoading';
import Ionicons from '@expo/vector-icons/Ionicons';

export const LeitorReconhecimentoFacial: React.FC<LeitorReconhecimentoFacialScreenProps> = ({ navigation, route }) => {
  const [temPermissao, setTemPermissao] = useState<boolean | null>(null);
  const [escaneado, setEscaneado] = useState(false);
  const [dadoEscaneado, setDadoEscaneado] = useState<string | null>(null);
  const [cameraRef, setCameraRef] = useState<Camera | null>(null); // Referência para a câmera
  const [loading, setLoading] = useState<boolean>(false)

  const [facing, setFacing] = useState<CameraType>('back');
  function toggleCameraFacing() {
    setFacing(current => (current === 'back' ? 'front' : 'back'));
  }

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setTemPermissao(status === 'granted');
    })();
  }, []);


  const handleFaceEscaneada = async ({ type, data }: any) => {
    const photo = await cameraRef.takePictureAsync({ base64: true });
    const { alunoId } = route.params

    setDadoEscaneado(photo.base64);
    setLoading(true)
    setEscaneado(true);
    if(cameraRef){
      try {
        const acesso : AcessoCadastroProps = {
          dispositivoId: Config.dispositivoIdAcesso,
          alunoId: alunoId,
          faceEntrada: photo.base64,
        }
        
        const response = await registrarDadosAcesso(acesso);
        Alert.alert('OK', `Entrada do aluno ${response.alunoId} registrada com sucesso`, [{ text: 'OK', onPress: () => {
          setEscaneado(false)
          navigation.navigate('Menu')
        } }])
      } catch (e) {
          Alert.alert('Erro', 'Não autorizado. Rosto não reconhecido.', [{ text: 'OK', onPress: () => setEscaneado(false) }]);
      } finally{
        setLoading(false)
      }
    }
  };

  if (temPermissao === null) {
    return <Text>Solicitando permissão para a câmera...</Text>;
  }
  if (temPermissao === false) {
    return <Text>Sem acesso à câmera</Text>;
  }

  
  return (
    <View style={styles.container}> 
      <CameraView style={styles.camera} facing={facing} ref={ref => setCameraRef(ref)}>
        <View style={styles.buttonContainer}>
          <Text style={styles.text}>Faça o reconhecimento facial</Text>
        </View>
        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.button} onPress={handleFaceEscaneada}>
            <Text style={styles.text}>Tirar foto</Text>
          </TouchableOpacity>
          <TouchableOpacity style={styles.buttonIcon} onPress={toggleCameraFacing}>
            <Ionicons name="camera-reverse-outline" size={56} color="white" />
          </TouchableOpacity>
        </View>
      </CameraView>
      {loading && (<ModalAlertLoading loading={loading} texto='Validando...' />)}
    </View>
  )
  
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
  message: {
    textAlign: 'center',
    paddingBottom: 10,
  },
  camera: {
    flex: 1,
  },
  buttonContainer: {
    flex: 1,
    flexDirection: 'row',
    backgroundColor: 'transparent',
    margin: 64,
  },
  buttonIcon: {
    flex: 1,
    alignSelf: 'flex-end',
    alignItems: 'center',
  },
  button: {
    flex: 1,
    alignSelf: 'flex-end',
    alignItems: 'center',
    backgroundColor: '#1E90FF',
    paddingVertical: 12,
    paddingHorizontal: 20,
    borderRadius: 8,
  },
  text: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
  },
});
