import React from 'react'
import { Modal, View, ActivityIndicator, StyleSheet, Text } from 'react-native'

interface ModalAlertLoadingProps {
  loading: boolean;
  texto: string;
}

const ModalAlertLoading: React.FC<ModalAlertLoadingProps> = ({ loading, texto }) => {
  return (
    <Modal
      transparent={true}
      animationType="none"
      visible={loading}
      onRequestClose={() => {}}
    >
      <View style={styles.modalBackground}>
        <View style={styles.activityIndicatorWrapper}>
          <ActivityIndicator size="large" color="#0000ff" />
            <Text style={styles.loadingText}>{texto}</Text>
        </View>
      </View>
    </Modal>
  );
}
export default ModalAlertLoading;
const styles = StyleSheet.create({
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