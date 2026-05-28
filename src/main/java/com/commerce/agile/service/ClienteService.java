package com.commerce.agile.service;

import com.commerce.agile.dominio.ClienteDomain;
import com.commerce.agile.dto.cliente.RequestClienteDTO;
import com.commerce.agile.dto.cliente.ResponseClienteDTO;
import com.commerce.agile.entidade.Cliente;
import com.commerce.agile.mapper.cliente.ClienteMapper;
import com.commerce.agile.repository.ClienteRepository;
import com.commerce.agile.infraestrutura.seguranca.excecoes.JaExistenteException;
import com.commerce.agile.infraestrutura.seguranca.excecoes.NaoEncontradoException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseClienteDTO registrarNovoCliente(@Valid RequestClienteDTO dto) {

        if(clienteRepository.existsByNome(dto.nome())){
            throw new JaExistenteException("Cliente já existente");
        }

        ClienteDomain clienteDomain = new ClienteDomain(
                dto.nome(),
                dto.email(),
                passwordEncoder.encode(dto.senha()),
                dto.dataNascimento(),
                dto.cpf()

        );

        clienteDomain.setRole("ROLE_CLIENTE");

        Cliente salvo = clienteMapper.toEntityFromDomain(clienteDomain);
        Cliente clienteSalvo = clienteRepository.save(salvo);

        return clienteMapper.toDTOFromEntity(clienteSalvo);

    }

    @Transactional
    public void excluirCliente(Long id) {
        buscarClientePeloId(id);

        clienteRepository.deleteById(id);

    }

    @Transactional
    public ResponseClienteDTO atualizarDadosCliente(Long id, @Valid RequestClienteDTO dto) {

        Cliente encontrado = clienteRepository.findById(id).orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado"));

        encontrado.setNome(dto.nome());
        encontrado.setEmail(dto.email());

        clienteRepository.save(encontrado);

        return clienteMapper.toDTOFromEntity(encontrado);

    }

    public Optional<ResponseClienteDTO> buscarClientePeloId(Long id){
        Optional<Cliente> cliente = Optional.of(clienteRepository.findById(id)
                .orElseThrow(() -> new NaoEncontradoException("Cliente não encontrado")));

        ResponseClienteDTO response =  clienteMapper.toDTOFromEntity(cliente.get());

        return  Optional.of(response);
    }
}
