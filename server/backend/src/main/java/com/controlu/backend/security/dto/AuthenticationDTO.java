package com.controlu.backend.security.dto;

public record AuthenticationDTO(String usuarioNome, String usuarioSenha) {
    @Override
    public String toString() {
        return "AuthenticationDTO{" +
                "usuarioNome='" + usuarioNome + '\'' +
                ", usuarioSenha='" + usuarioSenha + '\'' +
                '}';
    }
}
