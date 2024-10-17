import * as React from 'react';
import { styled, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar, { AppBarProps as MuiAppBarProps } from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import LogoutIcon from '@mui/icons-material/Logout';
import { mainListItems, secondaryListItems } from '../components/listItems';
import Copyright from '../components/Copyright';
import MenuIcon from '@mui/icons-material/Menu';

import { theme } from '../assets/theme';
import { useLocation, Outlet } from 'react-router-dom';
import Grid from '@mui/material/Grid';
import { removerAuthToken } from '../utils/TokenUtils';
import { useNavigate } from 'react-router-dom';
import { CardMedia } from '@mui/material';
import logo from '../assets/img/logo-sem-slogan-2.png'


const drawerWidth: number = 240;

interface AppBarProps extends MuiAppBarProps {
  open?: boolean;
}


const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})<AppBarProps>(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    '& .MuiDrawer-paper': {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      boxSizing: 'border-box',
      ...(!open && {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
          easing: theme.transitions.easing.sharp,
          duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);


export default function Main() {
  const [open, setOpen] = React.useState(true);
  const navigate = useNavigate()
  const toggleDrawer = () => {
    setOpen(!open);
  };

  const handleClickLogout = () => {
    removerAuthToken()
    navigate("/");
  }

  const location = useLocation();
  const getTitle = (pathname: string) => {
    switch (pathname) {
      case '/app/dashboard':
        return 'Administrativo / Dashboard';

      case '/app/relatorios':
          return 'Administrativo / Relatório';

      case '/app/aluno':
        return 'Cadastro / Aluno';

      case '/app/professor':
        return 'Cadastro / Professor';

      case '/app/disciplina':
        return 'Cadastro / Disciplina';

      case '/app/sala':
        return 'Cadastro / Sala';

      case '/app/cartao':
        return 'Cadastro / Cartão';

      case '/app/grade':
        return 'Cadastro / Grade';
        
      case '/app/curso':
        return 'Cadastro / Curso';
    
      case '/app/dispositivo':
        return 'Cadastro / Dispositivo de Leitura';

      case '/app/usuario':
        return 'Cadastro / Usuário do Sistema';
      
      default:
        return 'ControlU';
    }
  };
  const titulo = getTitle(location.pathname);

  return (
    <ThemeProvider theme={theme}>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <AppBar  open={open}  sx={{ backgroundColor: '#115571' }}>
          <Toolbar sx={{pr: '24px'}} >
            <IconButton
              edge="start"
              color="inherit"
              aria-label="open drawer"
              onClick={toggleDrawer}
              sx={{
                marginRight: '36px',
                ...(open && { display: 'none' }),
              }}
            >
              <MenuIcon />
            </IconButton>
            <CardMedia
              component="img"
              sx={{ height: 50, width: 'auto', marginRight: 2 }}
              image={logo} 
              alt="Logo ControlU"
            />
            <Typography
              component="h1"
              variant="h6"
              color="inherit"
              noWrap
              sx={{ flexGrow: 1 }}
            >
              {titulo}
            </Typography>

            <IconButton color="inherit" onClick={handleClickLogout}><LogoutIcon /></IconButton>
          </Toolbar>
        </AppBar>
        <Drawer variant="permanent" open={open} >
          <Toolbar
            sx={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'flex-end',
              px: [1],
            }}
          >
            <IconButton onClick={toggleDrawer}>
              <ChevronLeftIcon />
            </IconButton>
          </Toolbar>
          <Divider />
          <List component="nav">
            {mainListItems}
            <Divider sx={{ my: 1 }} />
            {secondaryListItems}
          </List>
        </Drawer>
        
        <Box
          component="main"
          sx={{
            backgroundColor: (theme) =>
              theme.palette.mode === 'light'
                ? theme.palette.grey[100]
                : theme.palette.grey[900],
            flexGrow: 1,
            height: '100vh',
            overflow: 'auto',
          }}
        >
          <Toolbar />
          <Container maxWidth="lg" sx={{ p: 5 }} >
            <Grid container spacing={3}>
              <Outlet />
            </Grid>
            <Copyright sx={{ pt: 4 }} />
          </Container>
        </Box>
      </Box>
    </ThemeProvider>
  );
}