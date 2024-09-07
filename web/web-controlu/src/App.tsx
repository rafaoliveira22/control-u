import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Dashboard from './screen/Dashboard';
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { theme } from './assets/theme';
import Aluno from './screen/cadastro/Aluno';
import Main from './screen/Main';
import Professor from './screen/cadastro/Professor';
import Disciplina from './screen/cadastro/Disiciplina';
import Dispositivo from './screen/cadastro/Dispositivo';
import CartaLeitura from './screen/cadastro/CartaoLeitura';
import Curso from './screen/cadastro/Curso';
import Sala from './screen/cadastro/Sala';
import Grade from './screen/cadastro/Grade';
import Usuario from './screen/cadastro/Usuario';
import SignIn from './screen/SignIn';

const App: React.FC = () => {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/app" element={<Main />}>
            <Route index path="dashboard" element={<Dashboard />} />
            <Route path="relatorios" element={<Aluno />} />
            <Route path="aluno" element={<Aluno />} />
            <Route path="professor" element={<Professor />} />
            <Route path="cartao" element={<CartaLeitura />} />
            <Route path="disciplina" element={<Disciplina />} />
            <Route path="sala" element={<Sala />} />
            <Route path="curso" element={<Curso />} />
            <Route path="grade" element={<Grade />} />
            <Route path="dispositivo" element={<Dispositivo />} />
            <Route path="usuario" element={<Usuario />} />
          </Route>
        </Routes>
      </Router>
    </ThemeProvider>
  );
};

export default App;