package com.user.user_app.cadastros.usuario.interfaces.converter;

import com.user.library.crud.interfaces.converter.CrudConverter;
import com.user.user_app.cadastros.usuario.domain.model.Usuario;
import com.user.user_app.cadastros.usuario.interfaces.dto.UsuarioDto;
import org.mapstruct.Mapper;

@Mapper
public abstract class UsuarioConverter extends CrudConverter<Usuario, UsuarioDto> {
}
