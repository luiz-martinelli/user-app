package com.user.user_app.cadastros.usuario.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USU_ID")
    private Integer id;

    @Column(name = "USU_NOME", length = 255)
    private String nome;

    @Column(name = "USU_EMAIL", length = 100, unique = true)
    private String email;

    @Column(name = "USU_SENHA", length = 255)
    private String senha;

}
