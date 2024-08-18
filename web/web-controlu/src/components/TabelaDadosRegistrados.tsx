import * as React from 'react';
import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './dashboard/Title';
import { DadosRegistradosProps } from '../interface/DadosRegistradosProps';

function preventDefault(event: React.MouseEvent) {
  event.preventDefault();
}

export default function TabelaDadosRegistrados(props: DadosRegistradosProps) {
  return (
    <React.Fragment>
      <Title>{props.titulo}</Title>
      <Table size="small">
        <TableHead>
        <TableRow>
          {props.campos.map((campo, i) => (
            <TableCell key={i}>{campo}</TableCell>
          ))}
        </TableRow>
        </TableHead>
        <TableBody>
          {props.dados.map((dado, i) => (
            <TableRow key={i}>
              {dado.map((item, j) =>
                <TableCell key={j}>{item}</TableCell>
              )}
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Link color="primary" href="#" onClick={preventDefault} sx={{ mt: 3 }}>
        Obter detalhes
      </Link>
    </React.Fragment>
  );
}