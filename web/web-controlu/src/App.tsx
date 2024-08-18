import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import SignIn from './screen/SignIn';
import Dashboard from './screen/Dashboard';
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { theme } from './assets/theme';
import Aluno from './screen/Aluno';
import Main from './screen/Main';
import Professor from './screen/Professor';

const App: React.FC = () => {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Routes>
          <Route path="/" element={<SignIn />} />
          <Route path="/dashboard" element={<Main />}>
            <Route index element={<Dashboard />} />
            <Route path="relatorios" element={<Aluno />} />
            <Route path="aluno" element={<Aluno />} />
            <Route path="professor" element={<Professor />} />
            <Route path="cartao" element={<Aluno />} />
            <Route path="disciplina" element={<Aluno />} />
            <Route path="sala" element={<Aluno />} />
            <Route path="curso" element={<Aluno />} />
            <Route path="grade" element={<Aluno />} />
            <Route path="dispositivo" element={<Aluno />} />
            <Route path="usuario" element={<Aluno />} />
            <Route path="nivel_acesso" element={<Aluno />} />
          </Route>
        </Routes>
      </Router>
    </ThemeProvider>
  );
};

export default App;