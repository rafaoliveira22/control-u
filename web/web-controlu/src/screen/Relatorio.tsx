import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';

import { Toaster, toast } from 'sonner';


export default function Relatorio() { 
  useEffect(() => {
    fetchDados();
  }, [])

  const fetchDados = async () => {
  }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    
  };

  return (
    <>
      <Grid item xs={12}>
        <Toaster richColors expand={true} closeButton />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
            <TextField
              margin="normal"
              required
              fullWidth
              id="nome"
              label="Tipo"
              name="tipo"
              autoComplete="tipo"
              autoFocus
              onChange={(e) => {alert(e.target.value)}}
              value={1}
            />
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>Gerar</Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <h2>Relatorio</h2>
      </Grid>
    </>
  );
}