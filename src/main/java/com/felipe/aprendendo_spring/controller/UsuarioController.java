package com.felipe.aprendendo_spring.controller;

import com.felipe.aprendendo_spring.business.UsuarioService;
import com.felipe.aprendendo_spring.controller.dtos.UsuarioDTO;
import com.felipe.aprendendo_spring.infrastructure.entity.Usuario;
import com.felipe.aprendendo_spring.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor//Injeçao de dependencia

public class UsuarioController {

    private final UsuarioService usuarioService;//Injeçao de dependencia
    private final AuthenticationManager authenticationManager; //Injeçao de dependencia
    private final JwtUtil jwtUtil;//Injeçao de dependencia


    @PostMapping
    public ResponseEntity<Usuario> salvaUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuario));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return jwtUtil.generateToken(authentication.getName());
    }
}
