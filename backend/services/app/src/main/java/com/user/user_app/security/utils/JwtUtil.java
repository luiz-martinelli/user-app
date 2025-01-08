package com.user.user_app.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String gerarToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(secretKey)
                .compact();
    }

    public Claims extrairClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extrairUsername(String token) {
        return extrairClaims(token).getSubject();
    }

    public Date extrairDataExpiracao(String token) {
        return extrairClaims(token).getExpiration();
    }

    private boolean isTokenExpirado(String token) {
        return extrairDataExpiracao(token).before(new Date());
    }

    public boolean validarToken(String token, String username) {
        return (username.equals(extrairUsername(token)) && !isTokenExpirado(token));
    }
}