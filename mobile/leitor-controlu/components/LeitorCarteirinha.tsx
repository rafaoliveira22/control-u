import { BarcodeScanningResult, CameraView } from "expo-camera";
import { View, StyleSheet } from "react-native";
import ModalAlertLoading from "./ModalAlertLoading";
import { useState } from "react";

interface LeitorCarteirinhaPros{
  onBarcodeScanned: (scanningResult: BarcodeScanningResult) => void;
  loading: boolean
}

export const LeitorCarteirinha: React.FC<LeitorCarteirinhaPros> =({ onBarcodeScanned, loading }) => {
  return (
    <>
      <CameraView
        onBarcodeScanned={onBarcodeScanned}
        style={styles.camera}
        barcodeScannerSettings={{
          barcodeTypes: ['ean13', 'ean8', 'upc_e', 'code39', 'code93', 'itf14', 'codabar', 'code128', 'upc_a'],
        }}
      />
      {/* Modal com loader */}
      {loading && (
        <ModalAlertLoading loading={loading} texto='Validando...' />
      )}
    </>
  );
}

const styles = StyleSheet.create({
  camera: {
    width: '80%', 
    height: '50%',
  },  
});