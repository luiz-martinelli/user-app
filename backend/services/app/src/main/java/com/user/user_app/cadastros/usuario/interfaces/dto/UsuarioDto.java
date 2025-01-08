package com.user.user_app.cadastros.usuario.interfaces.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {

    private Integer id;

    private String nome;

    private String email;

    private String senha;
}
