import React, { useState, useEffect } from 'react';
import { Text, View, Button, StyleSheet, TouchableOpacity, Alert } from 'react-native';
import { Camera, CameraView } from 'expo-camera'; // Importação correta do Camera
import { BarcodeScanningResult } from 'expo-camera/build/Camera.types';
import { LeitorAulaScreenProps } from '../props/ScreenProps';
import { verificarSeEstaRegistrado } from '../http/HttpClientAluno';
import { LeitorCarteirinha } from '../components/LeitorCarteirinha';
import Config from '../config/Config';
import { registrarDadosAula } from '../http/HttClientAula';
import { registrarDadosPresenca } from '../http/HttpClientPresenca';
import { PresencaProps } from '../props/PresencaProps';
import { AulaProps } from '../props/AulaProps';

export const LeitorAula: React.FC<LeitorAulaScreenProps> = ({ navigation }) => {
  const [temPermissao, setTemPermissao] = useState<boolean | null>(null);
  const [escaneado, setEscaneado] = useState(false);
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

    /**
     * VERIFICAR SE É UMA LEITURA DE CARTEIRINHA OU DE CARTÃO DE AULA
     * 
     * CARTÃO DE AULA
     *  - VALIDAR SE É UMA AULA ABERTA, SE FOR, FECHAR A AULA, SENÃO, FAZER ABERTURA DA AULA
     *  - ABERTURA: NOVO REGISTRO EM "AULA" (AULA_ID, GRADE_ID E HORARIO DE ABERTURA)
     *  - FECHAMENTO: ATUALIZAR O REGISTRO DA AULA COM O HORÁRIO DE FECHAMENTOD DA AULA (É CONSIDERADO FECHAMENTO
     *    A 2° VEZ QUE O CARTÃO DE AULA É LIDO)
     * 
     * CARTEIRINHA
     *  - VALIDAR SE O ALUNO ESTA REGISTRADO NA BASE DE DADOS
     *  - VALIDAR SE O ALUNO TEM UM REGISTRO DE ACESSO EM ABERTO, OU SEJA,
     *    SE ELE ACESSOU A FACULDADE PELO CONTROLE DE ACESSO.
     *  - VALIDAR SE A AULA ESTA ABERTA
     *  - AULA ABERTA: NOVO REGISTRO EM "PRESENCA" (ALUNO_ID, AULA_ID E HORARIO DE ENTRADA)
     *    QUALQUER OUTRA LEITURA DO ALUNO NA AULA É CONSIDERADA SAÍDA,SENDO ASSIM,
     *    DEVE-SE ATUALIZAR O REGISTRO DA PRESENÇA COM O HORÁRIO DE SAÍDA DA MESMA.
     */
    try {
      if(dadoEscaneadoTrim.startsWith("283") && dadoEscaneadoTrim.length === 13){
        // LEITURA DE CARTEIRINHA
        const presenca : PresencaProps = {
          dispositivoId: Config.dispositivoIdAula,
          alunoId: dadoEscaneadoTrim
        }
  
        const response = await registrarDadosPresenca(presenca);
        Alert.alert('OK', `Presença do aluno ${dadoEscaneadoTrim} registrada/atualizada com sucesso`, [{ text: 'OK', onPress: () => setEscaneado(false) }])
      } else if(dadoEscaneadoTrim.includes("C") && dadoEscaneadoTrim.length === 4){
        // LEITURA DE CARTÃO DE AULA
        const aula : AulaProps = {
          dispositivoId: Config.dispositivoIdAula,
          cartaoId: dadoEscaneadoTrim
        }
        const response = await registrarDadosAula(aula);
        let message
        if(response.aulaFechamento === null){
          message = 'aberta'
        } else{
          message = 'fechada'
        }
        Alert.alert('OK', `Aula ${message} com sucesso. Grade ${response.gradeId}`, [{ text: 'OK', onPress: () => setEscaneado(false) }])
      } else{
        throw new Error(`O dado escaneado, ${dadoEscaneadoTrim}, não é uma carteirinha nem um cartão de aula! Tente novamente ou contate o suporte.`)
      }
    } catch (e) {
      if(e instanceof Error){
        if(e.message.toLowerCase().includes("json") || e.message.toLowerCase().includes("sql")){
          Alert.alert('Erro', 'Erro inesperado! Tente novamente ou contate o suporte.', [{ text: 'OK', onPress: () => setEscaneado(false) }]);
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
    alignItems: 'center',
  },
});
