import React, { useState } from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import FormLabel from '@mui/material/FormLabel';
import FormControl from '@mui/material/FormControl';
import TextField from '@mui/material/TextField';
import Stack from '@mui/material/Stack';
import MuiCard from '@mui/material/Card';
import { styled } from '@mui/material/styles';
import { CardMedia } from '@mui/material';
import logo from '../assets/img/logo.png'
import { Toaster, toast } from 'sonner';
import { useNavigate } from 'react-router-dom';
import { UsuarioLoginProps } from '../interface/UsuarioProps';
import { fazerLogin } from '../http/HttpClientUsuario';
import { salvarAuthToken } from '../utils/TokenUtils';

  
  const Card = styled(MuiCard)(({ theme }) => ({
    display: 'flex',
    flexDirection: 'column',
    alignSelf: 'center',
    width: '100%',
    padding: theme.spacing(4),
    gap: theme.spacing(2),
    margin: 'auto',
    [theme.breakpoints.up('sm')]: {
      maxWidth: '450px',
    },
    boxShadow:
      'hsla(220, 30%, 5%, 0.05) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.05) 0px 15px 35px -5px',
    ...theme.applyStyles('dark', {
      boxShadow:
        'hsla(220, 30%, 5%, 0.5) 0px 5px 15px 0px, hsla(220, 25%, 10%, 0.08) 0px 15px 35px -5px',
    }),
  }));
  
  const SignInContainer = styled(Stack)(({ theme }) => ({
    minHeight: '100vh', 
    minWidth: '100vw',
    display: 'flex',
    justifyContent: 'center', // Centraliza verticalmente
    alignItems: 'center', // Centraliza horizontalmente
    padding: theme.spacing(2),
    [theme.breakpoints.up('sm')]: {
      padding: theme.spacing(4),
    },
    '&::before': {
      content: '""',
      display: 'block',
      position: 'absolute',
      zIndex: -1,
      inset: 0,
      backgroundImage:
        'radial-gradient(ellipse at 50% 50%, hsl(210, 100%, 97%), hsl(0, 0%, 100%))',
      backgroundRepeat: 'no-repeat',
      ...theme.applyStyles('dark', {
        backgroundImage:
          'radial-gradient(at 50% 50%, hsla(210, 100%, 16%, 0.5), hsl(220, 30%, 5%))',
      }),
    },
  }));

  
  export default function SignIn(props: { disableCustomTheme?: boolean }) {
    const [usuarioNome, setUsuarioNome] = useState<string>('')
    const [usuarioSenha, setUsuarioSenha] = useState<string>('')
    const navigate = useNavigate();


    const validarDadosFormulario = () => {
      if(!usuarioNome){
        return 'Nome do usuário'
      }
  
      if(!usuarioSenha){
        return 'Senha'
      }
  
      return false;
    }
    
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
            if(error.message === 'Failed to fetch'){
              error.message  = 'Erro ao fazer login! Tente novamente ou contate o suporte.'
            }
            return error.message
          }
        })
      } else{
        toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
      }
    };

    return (
      <>
        <Toaster richColors expand={true} closeButton />
        <CssBaseline enableColorScheme />
        <SignInContainer direction="column" justifyContent="space-between">
          <Card variant="outlined">
            <CardMedia
              component="img"
              height="140"
              image={logo} 
              alt="Logo ControlU"
            />
            <Box
              component="form"
              onSubmit={handleSubmit}
              noValidate
              sx={{
                display: 'flex',
                flexDirection: 'column',
                width: '100%',
                gap: 2,
              }}
            >
              <FormControl>
                <FormLabel htmlFor="usuario">Usuário</FormLabel>
                <TextField
                  id="usuario"
                  type="text"
                  name="usuario"
                  placeholder="nome de usuário"
                  autoComplete="usuario"
                  autoFocus
                  required
                  fullWidth
                  variant="outlined"
                  color={'primary'}
                  onChange={(e) => {setUsuarioNome(e.target.value as string)}}
                  value={usuarioNome}
                />
              </FormControl>
              <FormControl>
                <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                  <FormLabel htmlFor="password">Senha</FormLabel>
                </Box>
                <TextField
                  name="password"
                  placeholder="••••••"
                  type="password"
                  id="password"
                  autoComplete="current-password"
                  autoFocus
                  required
                  fullWidth
                  variant="outlined"
                  onChange={(e) => {setUsuarioSenha(e.target.value as string)}}
                  value={usuarioSenha}
                />
              </FormControl>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ backgroundColor: '#115571', '&:hover': { backgroundColor: '#215571' } }}
                >
                Sign in
              </Button>
            </Box>
          </Card>
        </SignInContainer>
      </>
    );
  }