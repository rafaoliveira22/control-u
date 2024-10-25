import React, { useState, useEffect } from 'react';
import { Text, View, Button, StyleSheet, TouchableOpacity, Alert } from 'react-native';
import { Camera, CameraView } from 'expo-camera'; // Importação correta do Camera
import { BarcodeScanningResult } from 'expo-camera/build/Camera.types';
import { LeitorCarteirinhaAcessoScreenProps } from '../props/ScreenProps';
import { LeitorCarteirinha } from '../components/LeitorCarteirinha';
import Config from '../config/Config';
import { registrarDadosAula } from '../http/HttClientAula';
import { registrarDadosPresenca } from '../http/HttpClientPresenca';
import { AcessoCadastroProps, AcessoProps } from '../props/AcessoProps';
import { registrarDadosAcesso, verificarSeTemAcessoEmAberto } from '../http/HttpClientAcesso';
import { verificarSeEstaRegistrado } from '../http/HttpClientAluno';

export const LeitorCarteirinhaAcesso: React.FC<LeitorCarteirinhaAcessoScreenProps> = ({ navigation }) => {
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
    const dadoEscaneadoTrim = data.trim()

    setLoading(true)
    setEscaneado(true);
    try {
        const response = await verificarSeEstaRegistrado(dadoEscaneadoTrim);
        if(response.estaRegistrado){
          const response = await verificarSeTemAcessoEmAberto(dadoEscaneadoTrim);
          if(response.temAcessoEmAberto){
            const acesso : AcessoCadastroProps = {
              dispositivoId: Config.dispositivoIdAcesso,
              alunoId: dadoEscaneadoTrim,
              faceEntrada: null,
            }
            const response = await registrarDadosAcesso(acesso);
            Alert.alert('OK', `Acesso do aluno ${response.alunoId} atualizado com sucesso`, [{ text: 'OK', onPress: () => {
              setEscaneado(false)
              navigation.navigate('Menu')
            } }])
          } else{
            navigation.navigate('LeitorReconhecimentoFacial', { alunoId: dadoEscaneadoTrim } )
          }
        } else{
          Alert.alert('Acesso não autorizado', `O aluno ${dadoEscaneadoTrim} não está na registrado na base de dados.`, [{ text: 'OK', onPress: () => setEscaneado(false) }]);
        }
      } catch (e) {
      if(e instanceof Error){
        if(e.message.toLowerCase().includes("json") || e.message.toLowerCase().includes("sql")){
          Alert.alert('Erro', 'Erro inesperado! Tente novamente ou contate o suporte.', [{ text: 'OK', onPress: () => setEscaneado(false) }]);
          console.error(e.message)
        } else{
          Alert.alert('Erro', e.message, [{ text: 'OK', onPress: () => setEscaneado(false) }]);
        }
      } else {
        Alert.alert('Erro', 'Erro inesperado! Tente novamente ou contate o suporte.', [{ text: 'OK', onPress: () => setEscaneado(false) }]);
      }
    } finally{
      setLoading(false)
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
      <LeitorCarteirinha onBarcodeScanned={escaneado ? () => {} : handleBarCodeScanned} loading={loading}/>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
  },
});
