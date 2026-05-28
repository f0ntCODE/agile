package com.commerce.agile.infraestrutura.seguranca.userDetails;

import com.commerce.agile.entidade.Cliente;
import com.commerce.agile.infraestrutura.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.repository.ClienteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClienteRepository clienteRepository;

    public CustomUserDetailsService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws NaoEncontradoException {
        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(() -> new NaoEncontradoException("cliente não encontrado"));

        return new CustomUserDetails(cliente);

    }
}
