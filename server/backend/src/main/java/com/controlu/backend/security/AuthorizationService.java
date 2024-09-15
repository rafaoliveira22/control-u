package com.controlu.backend.security;

import com.controlu.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> userDetails = repository.findByUsuarioNome(username);
        if (userDetails.isEmpty()) {
            throw new UsernameNotFoundException("Usuário inexistente ou senha inválida");
        }
        return userDetails.get();
    }
}
