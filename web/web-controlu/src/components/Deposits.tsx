import * as React from 'react';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import Title from './Title';

function preventDefault(event: React.MouseEvent) {
  event.preventDefault();
}

export default function Deposits() {
  return (
    <React.Fragment>
      <Title>Alunos</Title>
      <Typography component="p" variant="h4">
        000
      </Typography>
      <Typography color="text.secondary" sx={{ flex: 1 }}>
        teste
      </Typography>
      <div>
        <Link color="primary" href="#" onClick={preventDefault}>
          Obter detalhes
        </Link>
      </div>
    </React.Fragment>
  );
}