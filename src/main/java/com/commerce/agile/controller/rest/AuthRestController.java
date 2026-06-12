package com.commerce.agile.controller.rest;

import com.commerce.agile.dto.auth.AuthResponseDTO;
import com.commerce.agile.dto.auth.LoginRequestDTO;
import com.commerce.agile.dto.cliente.RequestClienteDTO;
import com.commerce.agile.dto.cliente.ResponseClienteDTO;
import com.commerce.agile.infraestrutura.seguranca.config.jwt.JwtTokenService;
import com.commerce.agile.infraestrutura.seguranca.userDetails.CustomUserDetails;
import com.commerce.agile.mapper.cliente.ClienteMapper;
import com.commerce.agile.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final ClienteService clienteService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final ClienteMapper clienteMapper;

    public AuthRestController(ClienteService clienteService,
                              AuthenticationManager authenticationManager,
                              JwtTokenService jwtTokenService,
                              ClienteMapper clienteMapper) {
        this.clienteService = clienteService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.clienteMapper = clienteMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenService.gerarToken(userDetails);
        ResponseClienteDTO cliente = clienteMapper.toDTOFromEntity(userDetails.getCliente());

        return ResponseEntity.ok(new AuthResponseDTO(token, "Bearer", cliente));
    }

    @PostMapping("/registrar")
    public ResponseEntity<AuthResponseDTO> registrar(@RequestBody @Valid RequestClienteDTO request) {
        ResponseClienteDTO cliente = clienteService.registrarNovoCliente(request);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String token = jwtTokenService.gerarToken(userDetails);

        return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponseDTO(token, "Bearer", cliente));
    }
}
