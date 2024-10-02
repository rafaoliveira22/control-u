package com.controlu.backend.security;

import com.controlu.backend.entity.model.Usuario;
import com.controlu.backend.security.dto.AuthenticationDTO;
import com.controlu.backend.security.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    /**
     * O UsernamePasswordAuthenticationToken representa um token de autenticação baseado em um nome de usuário e senha,
     * sendo assim, esses dados são encapsulados no token para que possam ser utilizados na autenticação.
     *
     * O token é passado para o authenticationManager.authenticate(usuarioSenha), que verificará as credenciais.
     * Se as credenciais forem válidas (usuário existe e a senha está correta), o processo de autenticação é bem-sucedido.
     *
     * @param data
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usuarioSenha = new UsernamePasswordAuthenticationToken(data.usuarioNome(), data.usuarioSenha());
        var auth = authenticationManager.authenticate(usuarioSenha);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
