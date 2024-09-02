package com.controlu.backend.repository;

import com.controlu.backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByUsuarioNome(String usuarioNome);
    UserDetails findByUsuarioNome(String usuarioNome);
}
