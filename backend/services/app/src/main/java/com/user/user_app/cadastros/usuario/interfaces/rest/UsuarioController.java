package com.user.user_app.cadastros.usuario.interfaces.rest;

import com.user.user_app.cadastros.usuario.domain.model.Usuario;
import com.user.user_app.cadastros.usuario.domain.service.UsuarioService;
import com.user.user_app.cadastros.usuario.interfaces.converter.UsuarioConverter;
import com.user.user_app.cadastros.usuario.interfaces.dto.IdDto;
import com.user.user_app.cadastros.usuario.interfaces.dto.UsuarioDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioConverter usuarioConverter;

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> buscarUsuarios(@PageableDefault Pageable pageable) {
        Page<Usuario> usuarios = usuarioService.getUsuarios(pageable);
        Page<UsuarioDto> usuarioDtos = usuarios.map(usuarioConverter::createDto);
        return new ResponseEntity<>(usuarioDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IdDto> criarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        Integer usuarioId  = usuarioService.createUsuario(usuarioConverter.createEntity(usuarioDto));
        return new ResponseEntity<>(new IdDto(usuarioId.longValue()), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editarUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        usuarioService.updateUsuario(usuarioConverter.createEntity(usuarioDto));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable("id") Integer usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
