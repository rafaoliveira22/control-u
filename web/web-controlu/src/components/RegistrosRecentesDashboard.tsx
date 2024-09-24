import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
import { Grid, Paper } from '@mui/material';
import { DashboardRegistrosRecentesProps } from '../interface/DashBoardProps';

export const RegistrosRecentesDashboard: React.FC<DashboardRegistrosRecentesProps> = ({ dados }) => {
  return (
    <Grid item xs={12}>
      <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
        <React.Fragment>
          <Title>Registros recentes</Title>
          <Table size="small">
            <TableHead>
              <TableRow>
                <TableCell>Data</TableCell>
                <TableCell>Horário</TableCell>
                <TableCell>Descrição</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {dados.map((row, index) => (
                <TableRow key={index}>
                  <TableCell>{row.data}</TableCell>
                  <TableCell>{row.horario}</TableCell>
                  <TableCell>{row.descricao}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </React.Fragment>
      </Paper>
    </Grid>
  );
};
