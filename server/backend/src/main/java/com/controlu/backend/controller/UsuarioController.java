package com.controlu.backend.controller;

import com.controlu.backend.service.UsuarioService;
import com.controlu.backend.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<?> obterDadosTodosUsuarios(){
        try{
            List<UsuarioVO> usuarios = service.obterDadosTodosUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> obterDadosUsuario(@PathVariable("id") String id){
        try{
            UsuarioVO usuario = service.obterDadosUsuario(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> registrarDadosUsuario(@RequestBody UsuarioVO usuario){
        try{
            UsuarioVO usuarioVO = service.registrarDadosUsuario(usuario);
            return new ResponseEntity<>(usuarioVO, HttpStatus.CREATED);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
