package com.commerce.agile.infraestrutura.seguranca.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenService {

    private final Dotenv dotenv;

    public JwtTokenService() {
        this.dotenv = Dotenv.load();
    }

    public String gerarToken(UserDetails userDetails) {
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(dotenv.get("EXPIRACAO_TOKEN"))))
                .sign(Algorithm.HMAC512(dotenv.get("JWT_SECRET")));
    }
}
