package com.felipe.aprendendo_spring.business;

import com.felipe.aprendendo_spring.infrastructure.entity.Usuario;
import com.felipe.aprendendo_spring.infrastructure.exceptions.ConflitExceptions;
import com.felipe.aprendendo_spring.infrastructure.exceptions.ResourceNotFoundException;
import com.felipe.aprendendo_spring.infrastructure.repository.UsuarioRepository;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //Injecao de dependencia

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public  Usuario salvaUsuario(Usuario usuario){
      try {
            emailExiste(usuario.getEmail());
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
          return usuarioRepository.save(usuario);
      } catch (ConflitExceptions e) {
        throw new ConflitExceptions("Email já cadastrado", e.getCause());
      }
    }

    public void emailExiste(String email){
        try {
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflitExceptions("Email já cadastrado"+ email);
            }
        } catch (ConflitExceptions e) {
            throw new RuntimeException("Email já cadastrado" + e.getCause());
        }
    }

    public  boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }
}
