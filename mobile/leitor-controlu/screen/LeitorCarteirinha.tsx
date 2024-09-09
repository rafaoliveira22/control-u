import React, { useState, useEffect } from 'react';
import { Text, View, Button, StyleSheet, TouchableOpacity, Alert } from 'react-native';
import { Camera, CameraView } from 'expo-camera'; // Importação correta do Camera
import { BarcodeScanningResult } from 'expo-camera/build/Camera.types';
import { LeitorCarteirinhaScreenProps } from '../props/ScreenProps';
import { verificarSeEstaRegistrado } from '../http/HttpClientAluno';
import ModalAlertLoading from '../components/ModalAlertLoading';

export const LeitorCarteirinha: React.FC<LeitorCarteirinhaScreenProps> = ({ navigation }) => {
  const [temPermissao, setTemPermissao] = useState<boolean | null>(null);
  const [escaneado, setEscaneado] = useState(false);
  const [dadoEscaneado, setDadoEscaneado] = useState<string>('');
  const [loading, setLoading] = useState<boolean>(false)

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setTemPermissao(status === 'granted');
    })();
  }, []);

  const handleBarCodeScanned = async ({ type, data }: BarcodeScanningResult) => {
    setLoading(true)
    setEscaneado(true);
    setDadoEscaneado(data.trim());

    /**
     * 1 - VALIDAR SE O R.A (dadoEscaneado) RETORNADO ESTÁ REGISTRADO NA BASE DE DADOS
     * 2 - AVANÇAR PARA TELA DE RECONHECIMENTO FACIAL
     */
    try {
      const response = await verificarSeEstaRegistrado(data);
      
      if (response.estaRegistrado) {
        Alert.alert('Sucesso', `O aluno ${data} está registrado`);
      } else {
        Alert.alert('Aviso', `O aluno ${data} não está registrado`);
      }
    } catch (e) {
      if(e instanceof Error){
        if(e.message.toLowerCase().includes("json")){
          Alert.alert('Erro', 'Erro inesperado! Tente novamente ou contate o suporte.');
        } else{
          Alert.alert('Erro', e.message);
        }
      } else {
        Alert.alert('Erro', 'Erro inesperado! Tente novamente ou contate o suporte.');
      }
    } finally{
      setLoading(false)
      setEscaneado(false);
      setDadoEscaneado("");
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
      <CameraView
        onBarcodeScanned={escaneado ? undefined : handleBarCodeScanned}
        style={styles.camera}
        barcodeScannerSettings={{
          barcodeTypes: ['ean13', 'ean8', 'upc_e', 'code39', 'code93', 'itf14', 'codabar', 'code128', 'upc_a'],
        }}
      />
      {/* Modal com loader */}
      {loading && (
        <ModalAlertLoading loading={loading} texto='Validando...' />
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  camera: {
    width: '80%', 
    height: '50%',
  },  
});
