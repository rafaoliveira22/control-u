import React from 'react';

import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Login } from './screen/Login';
import { LeitorCarteirinhaAcesso } from './screen/LeitorCarteirinhaAcesso';
import { LeitorAula } from './screen/LeitorAula';
import { Menu } from './screen/Menu';

export default function App() {
  const Stack = createNativeStackNavigator<RootStackParamList>();
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Login">
        <Stack.Screen name="Login" component={Login} options={{ title: ''}}/>
        <Stack.Screen name="Menu" component={Menu} options={{ title: ''}}/>
        <Stack.Screen name="LeitorCarteirinhaAcesso" component={LeitorCarteirinhaAcesso} options={{ title: ''}}/>
        <Stack.Screen name="LeitorAula" component={LeitorAula} options={{ title: ''}}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
}
