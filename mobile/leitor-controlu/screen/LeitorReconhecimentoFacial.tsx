import React, { useState, useEffect } from 'react';
import { Text, View, Button, StyleSheet } from 'react-native';


export default function LeitorReconhecimentoFacial(){
  return(
    <View style={styles.container}>
      <Text>Leitor Reconhecimento Facial</Text>
    </View>
  )
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});