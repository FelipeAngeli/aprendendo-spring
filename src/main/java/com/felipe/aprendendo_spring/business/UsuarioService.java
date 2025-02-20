package com.felipe.aprendendo_spring.business;

import com.felipe.aprendendo_spring.infrastructure.entity.Usuario;
import com.felipe.aprendendo_spring.infrastructure.exceptions.ConflitExceptions;
import com.felipe.aprendendo_spring.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //Injecao de dependencia

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public  Usuario salvaUsuario(Usuario usuario){
      try {
            emailExiste(usuario.getEmail());

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
}
