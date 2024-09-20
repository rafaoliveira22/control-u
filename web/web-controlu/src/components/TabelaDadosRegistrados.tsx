import * as React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
import { DadosRegistradosProps } from '../interface/DadosRegistradosProps';

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
          {props.dados.length > 0 ? (
            props.dados.map((dado, i) => (
              <TableRow key={i}>
                {dado.map((item, j) => (
                  <TableCell key={j}>{item}</TableCell>
                ))}
              </TableRow>
            ))
          ) : (
            <TableRow>
              <TableCell>Nenhum registro encontrado</TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </React.Fragment>
  );
}