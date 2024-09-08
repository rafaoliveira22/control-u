import React, { useState, useEffect } from 'react';
import { Text, View, Button, StyleSheet } from 'react-native';
import { Camera, CameraView } from 'expo-camera'; // Importação correta do Camera
import { BarcodeScanningResult } from 'expo-camera/build/Camera.types';
import { LeitorCarteirinhaScreenProps } from '../props/ScreenProps';

export const LeitorCarteirinha: React.FC<LeitorCarteirinhaScreenProps> = ({ navigation }) => {
  const [temPermissao, setTemPermissao] = useState<boolean | null>(null);
  const [escaneado, setEscaneado] = useState(false);
  const [dadoEscaneado, setDadoEscaneado] = useState<string>('');

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setTemPermissao(status === 'granted');
    })();
  }, []);

  const handleBarCodeScanned = ({ type, data }: BarcodeScanningResult) => {
    setEscaneado(true);
    setDadoEscaneado(data);

    /**
     * 1 - VALIDAR SE O R.A (dadoEscaneado) RETORNADO ESTÁ REGISTRADO NA BASE DE DADOS
     * 2 - AVANÇAR PARA TELA DE RECONHECIMENTO FACIAL
     */
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
      {escaneado && <Button title={'Escanear novamente'} onPress={() => setEscaneado(false)} />}
      {dadoEscaneado ? <Text>Dados escaneados: {dadoEscaneado}</Text> : null}
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
