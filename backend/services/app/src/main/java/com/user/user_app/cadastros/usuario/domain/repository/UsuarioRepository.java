package com.user.user_app.cadastros.usuario.domain.repository;

import com.user.library.crud.domain.repository.BaseJpaRepository;
import com.user.user_app.cadastros.usuario.domain.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UsuarioRepository extends BaseJpaRepository<Usuario> {

    List<Usuario> findByEmail (String email);
}
