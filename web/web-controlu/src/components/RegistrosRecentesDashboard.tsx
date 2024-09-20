import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
import { Grid, Paper } from '@mui/material';

function createData(id: number,date: string,hour: string,description: string) {
  return { id, date, hour, description };
}

const rows = [
  createData(0,'09/08/2024','19:02:03','Aula de redes aberta'),
  createData(1,'09/08/2024','19:01:58','Entrada do aluno 283000000007 na aula de inglês'),
  createData(2,'09/08/2024','19:01:45','Aula de inglês aberta'),
  createData(3,'09/08/2024','18:48:02','Entrada do aluno 283000000001'),
  createData(4,'09/08/2024','18:43:19','Entrada do aluno 283000000000'),
];

export default function RegistrosRecentesDashboard() {
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
              {rows.map((row) => (
                <TableRow key={row.id}>
                  <TableCell>{row.date}</TableCell>
                  <TableCell>{row.hour}</TableCell>
                  <TableCell>{row.description}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </React.Fragment>
      </Paper>
    </Grid>
  );
}