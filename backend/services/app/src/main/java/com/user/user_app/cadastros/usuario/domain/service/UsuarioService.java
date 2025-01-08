package com.user.user_app.cadastros.usuario.domain.service;

import com.user.user_app.cadastros.usuario.domain.model.Usuario;
import com.user.user_app.cadastros.usuario.domain.repository.UsuarioRepository;
import com.user.user_app.common.passwordencoder.domain.service.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoderService passwordEncoderService;

    public Page<Usuario> getUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Integer createUsuario(Usuario usuario) {
        validateEmailUniqueness(usuario.getEmail());
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setSenha(passwordEncoderService.hashPassword(usuario.getSenha()));
        usuarioRepository.save(novoUsuario);
        return novoUsuario.getId();
    }

    public void updateUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    private void validateEmailUniqueness(String email) {
        List<Usuario> usuarios = usuarioRepository.findByEmail(email);
        if (!usuarios.isEmpty()) {
            throw new RuntimeException("O e-mail já está cadastrado em outro usuário.");
        }
    }
}
