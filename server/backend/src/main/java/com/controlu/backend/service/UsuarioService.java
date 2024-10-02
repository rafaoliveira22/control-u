package com.controlu.backend.service;

import com.controlu.backend.controller.UsuarioController;
import com.controlu.backend.entity.model.Usuario;
import com.controlu.backend.exception.ResourceNotFoundException;
import com.controlu.backend.mapper.DozerMapper;
import com.controlu.backend.repository.UsuarioRepository;
import com.controlu.backend.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    /**
     * MÉTODO PARA OBTER TODOS OS USUÁRIOS REGISTRADOS NA BASE DE DADOS
     * @return LISTA COM DADOS DE TODOS OS USUÁRIOS
     */
    public List<UsuarioVO> obterDadosTodosUsuarios(){
        var usuarios = DozerMapper.parseListObjects(repository.findAll(), UsuarioVO.class);
        usuarios.stream().forEach(usuario -> {
            usuario.setUsuarioSenha(null);
            usuario.add(linkTo(methodOn(UsuarioController.class).obterDadosUsuario(String.valueOf(usuario.getUsuarioId()))).withSelfRel());
        });
        return usuarios;
    }

    /**
     * MÉTODO PARA OBTER OS DADOS DE UM USUARIO ESPECÍFICO
     * @param id IDENTIFICAÇÃO DO USUARIO
     * @return DADOS DO USUARIO
     */
    public UsuarioVO obterDadosUsuario(String id){
        var usuario = repository.findById(Integer.parseInt(id)).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse ID."));
        var vo = DozerMapper.parseObject(usuario, UsuarioVO.class);
        vo.setUsuarioSenha(null);
        vo.add(linkTo(methodOn(UsuarioController.class).obterDadosUsuario(id)).withSelfRel());

        return vo;
    }

    /**
     * MÉTODO PARA REGISTRAR UM NOVO USUARIO NA BASE DE DADOS
     * @param usuarioVO OBJETO CARREGADO COM OS DADOS DO USUARIO
     * @return USUARIO REGISTRADO
     */
    public UsuarioVO registrarDadosUsuario(UsuarioVO usuarioVO){
        if (repository.existsByUsuarioNome(usuarioVO.getUsuarioNome())) {
            throw new IllegalArgumentException("Nome de usuário em uso.");
        }

        Usuario usuario = DozerMapper.parseObject(usuarioVO, Usuario.class);
        usuario.setUsuarioSenha(new BCryptPasswordEncoder().encode(usuario.getUsuarioSenha()));
        var vo = DozerMapper.parseObject(repository.save(usuario), UsuarioVO.class);

        vo.setUsuarioSenha(null);
        vo.add(linkTo(methodOn(UsuarioController.class).obterDadosUsuario(String.valueOf(vo.getUsuarioId()))).withSelfRel());
        return vo;
    }
}
