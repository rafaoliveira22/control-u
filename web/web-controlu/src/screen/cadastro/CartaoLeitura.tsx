import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import { Toaster, toast } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import { CartaoProps } from '../../interface/CartaoProps';
import { obterDadosTodosCartoes, registrarDadosCartao } from '../../http/HttpClientCartao';
import config from '../../config/config';

export default function CartaoLeitura() {
  const [cartoes, setCartoes] = useState<CartaoProps[]>([]);
  
  useEffect(() => {
    fetchDados();
  }, [])

  const fetchDados = async () => {
    try{
      const dados: CartaoProps[] = await obterDadosTodosCartoes();
      const dadosConvertidos = dados.map(cartao => [
        cartao.cartaoId,
        config.cartao.status[cartao.status]
      ]);
      setCartoes(dadosConvertidos)
    } catch (error) {
      if (error instanceof Error) {
        error.message = "Erro ao obter dados dos cartões! Tente novamente ou contate o suporte."
        toast.error(error.message)
      } else {
        toast.error('Erro desconhecido')
      }
    }
  }

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
  
    const promise = registrarDadosCartao();
  
    toast.promise(promise, {
      loading: 'Cadastrando cartão...',
      success: () => {
        fetchDados();
        return "Cadastro realizado com sucesso";
      },
      error: (error) => {
        error.message = "Erro ao cadastrar cartão! Tente novamente ou contate o suporte.";
        return error.message
      },
    });
  };

  return (
    <>
      <Grid item xs={12}>
        <Toaster richColors expand={true} closeButton />
        <Box component="form" onSubmit={handleSubmit} noValidate sx={{ mt: 1 }}>
        <Box
            sx={{
              width: '300px',
              height: '150px',
              background: 'linear-gradient(to right, #fff, rgba(0,0,0,0.1))',
              borderRadius: '12px',
              boxShadow: '0px 4px 8px rgba(0, 0, 0, 0.2)',
            }}
          />
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>Gerão cartão de leitura</Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Cartões Registrados" 
            campos={["ID", "Status"]}
            dados={cartoes}
          />
        </Paper>
      </Grid>
    </>
  );
}