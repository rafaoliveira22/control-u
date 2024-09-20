import React, { useState } from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { Toaster, toast } from 'sonner';
import { useNavigate } from 'react-router-dom';
import { UsuarioLoginProps } from '../interface/UsuarioProps';
import { fazerLogin } from '../http/HttpClientUsuario';
import { removerAuthToken, salvarAuthToken } from '../utils/TokenUtils';
const theme = createTheme();

export default function SignIn() {
  const [usuarioNome, setUsuarioNome] = useState<string>('')
  const [usuarioSenha, setUsuarioSenha] = useState<string>('')
  const navigate = useNavigate();

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const validacaoDadosForms = validarDadosFormulario()
    if(!validacaoDadosForms){
      const usuario: UsuarioLoginProps = {
        usuarioNome: usuarioNome,
        usuarioSenha: usuarioSenha
      }

      const promise = fazerLogin(usuario)
      toast.promise(promise, {
        loading: "Entrando...",
        success: (resolve) => {
          setUsuarioNome("")
          setUsuarioSenha("")
          
          salvarAuthToken(resolve.token)
          console.log(`Salvando token... ${resolve.token}`)

          navigate('/app/dashboard')

          return "Login realizado com sucesso!"
        },
        error: (error) => {
          return error.message;
        }
      })
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };

  const validarDadosFormulario = () => {
    if(!usuarioNome){
      return 'Nome do usuário'
    }

    if(!usuarioSenha){
      return 'Senha'
    }

    return false;
  }

  return (
    <ThemeProvider theme={theme}>
      <Toaster richColors expand={true} closeButton />
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center'
          }}
        >
          
          <Typography component="h1" variant="h5">
            ControlU Login
          </Typography>
          <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }} >
            <TextField
              margin="normal"
              required
              fullWidth
              id="usuario"
              label="Usuário"
              name="usuario"
              autoComplete="usuario"
              autoFocus
              onChange={(e) => {setUsuarioNome(e.target.value as string)}}
              value={usuarioNome}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="senha"
              label="Senha"
              type="password"
              id="senha"
              autoComplete="current-password"
              onChange={(e) => {setUsuarioSenha(e.target.value as string)}}
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Entrar
            </Button>
            <Grid container>
              <Grid item xs>
                <Link href="#" variant="body2">
                  Forgot password?
                </Link>
              </Grid>
              <Grid item>
                <Link href="#" variant="body2">
                  {"Don't have an account? Sign Up"}
                </Link>
              </Grid>
            </Grid>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
