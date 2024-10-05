import React, { useState, useEffect } from 'react';
import { FormControl, InputLabel, Select, MenuItem } from '@mui/material';
import { Toaster, toast } from 'sonner';
import { SelectProps } from '../interface/SelectProps';
import { CartaoProps } from '../interface/CartaoProps';
import { obterDadosTodosCartoesQueNaoEstaoAssociadosAUmaGrade } from '../http/HttpClientCartao';

const CartaoSelect: React.FC<SelectProps> = ({ value, onChange, isRelatorio }) => {
  const [cartoes, setCartoes] = useState<CartaoProps[]>([]);

  useEffect(() => {
    const fetchDados = async () => {
      try {
        const data = await obterDadosTodosCartoesQueNaoEstaoAssociadosAUmaGrade();
        setCartoes(data);
      } catch (error) {
        if (error instanceof Error) {
          if(error.message.toLowerCase() === "failed to fetch"){
            error.message = "Erro ao carregar os cartões disponíveis! Tente novamente ou contate o suporte."
          }
          toast.error(error.message);
          const cartaoError: CartaoProps = {
            cartaoId: error.message,
            status: 0
          };
          setCartoes([cartaoError]);
        } else {
          toast.error('Erro desconhecido');
        }
      }
    };

    fetchDados();
  }, []);

  return (
    <FormControl fullWidth>
      <Toaster richColors  expand={true} />
      <InputLabel id="select-label">Cartão *</InputLabel>
      <Select
        labelId="select-label"
        id="select"
        value={value}
        label="Cartão de Leitura"
        onChange={onChange}
      >
        {cartoes.map((cartao) => (
          <MenuItem key={cartao.cartaoId} value={cartao.cartaoId}>
            {cartao.cartaoId} 
          </MenuItem>
        ))}
      </Select>
    </FormControl>
  );
};

export default CartaoSelect;
