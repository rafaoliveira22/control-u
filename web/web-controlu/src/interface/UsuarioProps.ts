export interface UsuarioProps {
  usuarioId: number,
  usuarioNome: string,
  nivelAcessoId: number
}

export interface UsuarioCadastroProps {
  usuarioNome: string,
  usuarioSenha: string,
  nivelAcessoId: number
}

export interface UsuarioLoginProps {
  usuarioNome: string,
  usuarioSenha: string,
}