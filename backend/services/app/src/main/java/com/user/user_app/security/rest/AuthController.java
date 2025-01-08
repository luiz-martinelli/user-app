package com.user.user_app.security.rest;

import com.user.user_app.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/public")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<JwtDto> token() {
        String token = jwtUtil.gerarToken("usuarioPadrao");
        return new ResponseEntity<>(new JwtDto(token), HttpStatus.OK);
    }
}
