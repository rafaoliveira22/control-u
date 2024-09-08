import React from 'react';

import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Login } from './screen/Login';
import { LeitorCarteirinha } from './screen/LeitorCarteirinha';

export default function App() {
  const Stack = createNativeStackNavigator<RootStackParamList>();
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={Login} options={{ title: ''}}/>
        <Stack.Screen name="LeitorCarteirinha" component={LeitorCarteirinha} options={{ title: ''}}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
}
