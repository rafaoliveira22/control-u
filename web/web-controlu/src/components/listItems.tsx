import * as React from 'react';
import ListSubheader from '@mui/material/ListSubheader';

// ICONS
import DashboardIcon from '@mui/icons-material/Dashboard';
import AssignmentIcon from '@mui/icons-material/Assignment';
import SchoolIcon from '@mui/icons-material/School';
import WcIcon from '@mui/icons-material/Wc';
import BookmarksIcon from '@mui/icons-material/Bookmarks';
import QrCodeScannerIcon from '@mui/icons-material/QrCodeScanner';
import BadgeIcon from '@mui/icons-material/Badge';import ClassIcon from '@mui/icons-material/Class';
import RoomPreferencesIcon from '@mui/icons-material/RoomPreferences';
import PersonIcon from '@mui/icons-material/Person';
import GradeIcon from '@mui/icons-material/Grade';

import ListItem from './ListItem';

export const mainListItems = (
  <React.Fragment>
    <ListSubheader component="div" inset>
        Administrativo
    </ListSubheader>

    <ListItem icon={DashboardIcon} primary="Dashboard" pathRouter="/app/dashboard" />
    <ListItem icon={AssignmentIcon} primary="Relatórios" pathRouter="/app/relatorios"/>
  </React.Fragment>
);

export const secondaryListItems = (
  <React.Fragment>
    <ListSubheader component="div" inset>
      Cadastros e Consultas
    </ListSubheader>

    <ListItem icon={WcIcon} primary="Aluno" pathRouter="aluno" />
    <ListItem icon={SchoolIcon} primary="Professor" pathRouter="professor" />
    <ListItem icon={BadgeIcon} primary="Cartão de leitura" pathRouter="cartao" />
    <ListItem icon={ClassIcon} primary="Disciplina" pathRouter="disciplina" />
    <ListItem icon={RoomPreferencesIcon} primary="Sala" pathRouter="sala" />
    <ListItem icon={GradeIcon} primary="Curso" pathRouter="curso" />
    <ListItem icon={BookmarksIcon} primary="Grade" pathRouter="grade" />
    <ListItem icon={QrCodeScannerIcon} primary="Dispositivo de leitura" pathRouter="dispositivo" />
    <ListItem icon={PersonIcon} primary="Usuario" pathRouter="usuario"/>
  </React.Fragment>
);