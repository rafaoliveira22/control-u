package com.controlu.backend.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.controlu.backend.entity.Usuario;
import com.controlu.backend.utils.Defines;
import com.controlu.backend.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}}")
    private String secret;
    public String gerarToken(Usuario usuario){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer(Defines.ISSUER_JWT)
                    .withSubject(usuario.getUsuarioNome())
                    .withIssuedAt(new Date())
                    .withExpiresAt(gerarDateExpiracao())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar o token JWT", e);
        }
    }

    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(Defines.ISSUER_JWT)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant gerarDateExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
