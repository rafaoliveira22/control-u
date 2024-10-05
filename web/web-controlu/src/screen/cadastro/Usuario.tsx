import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';

import Paper from '@mui/material/Paper';
import { Toaster, toast } from 'sonner';
import TabelaDadosRegistrados from '../../components/TabelaDadosRegistrados';
import config from '../../config/config';
import { UsuarioCadastroProps, UsuarioProps } from '../../interface/UsuarioProps';
import NivelAcessoSelect from '../../components/NivelAcessoSelect';
import { obterDadosTodosUsuarios, registrarDadosUsuario } from '../../http/HttpClientUsuario';

export default function Usuario() {
  const [nivelAcessoSelecionado, setNivelAcessoSelecionado] = useState<number | string>('')
  const [nome, setNome] = useState<string>("")
  const [senha, setSenha] = useState<string>("")
  const [usuarios, setUsuarios] = useState<UsuarioProps[]>([]);
  useEffect(() => {
    fetchDados();
  }, [])

  const fetchDados = async () => {
    try{
      const dados: UsuarioProps[] = await obterDadosTodosUsuarios();
      // Convertendo os dados dos alunos para o formato esperado pela tabela
      const dadosConvertidos = dados.map(usuario => [
        usuario.usuarioId,
        usuario.usuarioNome,
        config.nivel_acesso[usuario.nivelAcessoId],
      ]);
      setUsuarios(dadosConvertidos)
    } catch (error) {
      if (error instanceof Error) {
        if(error.message.toLowerCase() === "failed to fetch"){
          error.message = "Erro ao obter dados dos usuários! Tente novamente ou contate o suporte."
        } 
        toast.error(error.message)
      } else {
        toast.error('Erro desconhecido')
      }
    }
  }
  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const validacaoDadosForms = validarDadosFormulario()
    if(!validacaoDadosForms){
      const usuario: UsuarioCadastroProps = {
        usuarioNome: nome as string,
        usuarioSenha: senha as string,
        nivelAcessoId: Number(nivelAcessoSelecionado),
      };
  
      const promise = registrarDadosUsuario(usuario);
      toast.promise(promise, {
        loading: "Cadastrando usuário...",
        success: () => {
          setNome("")
          setSenha("")
          setNivelAcessoSelecionado("")
          fetchDados()

          return "Cadastro realizado com sucesso"
        },
        error: (error) => {
          const messageError = "Erro ao cadastrar usuário! Tente novamente ou contate o suporte.";
          if(error.message.toLowerCase() !== "Nome de usuário em uso.".toLowerCase()){
            error.message = messageError
          } 

          return error.message;
        }
      })
    } else{
      toast.warning(`O campo ${validacaoDadosForms} não foi informado corretamente`)
    }
  };

  const validarDadosFormulario = () => {
    if(!nome){
      return 'Nome'
    }


    if(!nivelAcessoSelecionado){
      return 'Nível de Acesso'
    }

    return false;
  }

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
              label="Nome do usuário para login"
              name="nome"
              autoComplete="nome"
              autoFocus
              onChange={(e) => {setNome(e.target.value as string)}}
              value={nome}
            />

            <TextField
              margin="normal"
              required
              fullWidth
              name="senha"
              label="Senha"
              type="password"
              id="senha"
              autoComplete="senha"
              onChange={(e) => {setSenha(e.target.value as string)}}
              value={senha}
            />
            <NivelAcessoSelect value={nivelAcessoSelecionado} onChange={(e) => setNivelAcessoSelecionado(e.target.value as number)} isRelatorio={false}/>
            <Button type="submit" variant="contained" sx={{ mt: 3, mb: 2 }}>Cadastrar</Button>
        </Box>
      </Grid>
      <Grid item xs={12}>
        <Paper sx={{ p: 2, display: 'flex', flexDirection: 'column' }}>
          <TabelaDadosRegistrados 
            titulo="Usuários Registrados" 
            campos={["ID", "Nome", "Nível de Acesso"]}
            dados={usuarios}
          />
        </Paper>
      </Grid>
    </>
  );
}