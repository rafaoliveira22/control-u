import { BarcodeScanningResult, CameraType, CameraView } from "expo-camera";
import { View, StyleSheet, TouchableOpacity, Text } from "react-native";
import ModalAlertLoading from "./ModalAlertLoading";
import { useState } from "react";
import Ionicons from '@expo/vector-icons/Ionicons';


interface LeitorCarteirinhaPros{
  onBarcodeScanned: (scanningResult: BarcodeScanningResult) => void;
  loading: boolean
}

export const LeitorCarteirinha: React.FC<LeitorCarteirinhaPros> =({ onBarcodeScanned, loading }) => {
  const [facing, setFacing] = useState<CameraType>('back');
  function toggleCameraFacing() {
    setFacing(current => (current === 'back' ? 'front' : 'back'));
  }
  
  return (
    <>
      <CameraView
        onBarcodeScanned={onBarcodeScanned}
        facing={facing}
        style={styles.camera}
        barcodeScannerSettings={{
          barcodeTypes: ['ean13', 'ean8', 'upc_e', 'code39', 'code93', 'itf14', 'codabar', 'code128', 'upc_a'],
        }}
      >
        <View style={styles.buttonContainer}>
          <TouchableOpacity style={styles.button} onPress={toggleCameraFacing}>
            <Ionicons name="camera-reverse-outline" size={64} color="white" />
          </TouchableOpacity>
        </View>
      </CameraView> 
      {/* Modal com loader */}
      {loading && (
        <ModalAlertLoading loading={loading} texto='Validando...' />
      )}
    </>
  );
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
  button: {
    flex: 1,
    alignSelf: 'flex-end',
    alignItems: 'center',
  },
  text: {
    fontSize: 24,
    fontWeight: 'bold',
    color: 'white',
  },
});