import * as React from 'react';
import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './dashboard/Title';
import { DadosRegistradosProps } from '../interface/DadosRegistradosProps';
import { Toaster, toast } from 'sonner';

function obterDetalhes(event: React.MouseEvent) {
  event.preventDefault();
  toast.warning("Recurso não disponível no momento. Em desenvolvimento...")
}

export default function TabelaDadosRegistrados(props: DadosRegistradosProps) {
  return (
    <React.Fragment>
      <Toaster richColors expand={true} closeButton />
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
      <Link color="primary" href="#" onClick={obterDetalhes} sx={{ mt: 3 }}>
        Obter detalhes
      </Link>
    </React.Fragment>
  );
}