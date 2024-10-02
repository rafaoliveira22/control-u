package com.controlu.backend.repository;

import com.controlu.backend.entity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByUsuarioNome(String usuarioNome);
    Optional<UserDetails> findByUsuarioNome(String usuarioNome);
}
