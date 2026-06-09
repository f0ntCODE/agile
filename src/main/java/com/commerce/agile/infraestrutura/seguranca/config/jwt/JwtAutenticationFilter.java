package com.commerce.agile.infraestrutura.seguranca.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.commerce.agile.dto.cliente.RequestClienteDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JwtAutenticationFilter extends UsernamePasswordAuthenticationFilter {
    private Dotenv dotenv;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public JwtAutenticationFilter(AuthenticationManager authenticationManager,
                                  ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        this.dotenv = Dotenv.load();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            RequestClienteDTO clienteDTO = objectMapper
                    .readValue(request.getInputStream(), RequestClienteDTO.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            clienteDTO.nome(),
                            clienteDTO.senha()
                    )
            );

        }catch (IOException ex){
            throw new RuntimeException("Falha ao autenticar usuário: " + ex);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetails user = (UserDetails) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(dotenv.get("EXPIRACAO_TOKEN"))))
                .sign(Algorithm.HMAC512(dotenv.get("JWT_SECRET")));

        response.getWriter().write(token);
        response.getWriter().flush();

    }
}
