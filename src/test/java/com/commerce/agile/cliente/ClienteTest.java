package com.commerce.agile.cliente;

import com.commerce.agile.dto.cliente.RequestClienteDTO;
import com.commerce.agile.dto.cliente.ResponseClienteDTO;
import com.commerce.agile.seguranca.excecoes.CpfInvalidoException;
import com.commerce.agile.seguranca.excecoes.EmaiIInvalidoException;
import com.commerce.agile.seguranca.excecoes.IdadeException;
import com.commerce.agile.seguranca.excecoes.NaoEncontradoException;
import com.commerce.agile.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClienteTest {

    @Autowired
    private ClienteService clienteService;

    @Nested
    class CorrectProcedureTest{

        @Autowired
        private ClienteService clienteService;

        @Test
        @DisplayName("Cliente deve ser registrado")
        void clienteDeveSerRegistrado(){

            LocalDate dataNascimento = LocalDate.of(1999, Month.JANUARY, 5);
            DateTimeFormatter formato =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento.format(formato);

            RequestClienteDTO dto = new RequestClienteDTO("Afonso", "af@email.com", "senha123", "12345678910", dataNascimento);

            ResponseClienteDTO cliente = clienteService.registrarNovoCliente(dto);

            assertTrue(clienteService.buscarClientePeloId(cliente.id()).isPresent());

        }

        @Test
        @DisplayName("Cliente deve ser excluído")
        void clienteDeveSerExcluido(){
            LocalDate dataNascimento = LocalDate.of(1999, Month.JANUARY, 5);
            DateTimeFormatter formato =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento.format(formato);

            RequestClienteDTO dto = new RequestClienteDTO("Afonso", "af@email.com", "senha123", "12345678910", dataNascimento);

            ResponseClienteDTO cliente = clienteService.registrarNovoCliente(dto);

            clienteService.excluirCliente(cliente.id());

            assertThrows(NaoEncontradoException.class, () ->{

                clienteService.buscarClientePeloId(cliente.id());

            });

        }


    }
    @Nested
    class IncorrectProcedureTest{

        @Test
        @DisplayName("Email não pode estar inválido")
        void emailNaoPodeEstarIncorreto(){
            LocalDate dataNascimento = LocalDate.of(1999, Month.JANUARY, 5);
            DateTimeFormatter formato =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento.format(formato);

            String emailInvalido = "af@mail..co";

            RequestClienteDTO dto = new RequestClienteDTO("Afonso", emailInvalido, "senha123", "12345678910", dataNascimento);

            assertThrows(EmaiIInvalidoException.class, () ->{

                clienteService.registrarNovoCliente(dto);
            });

        }

        @Test
        @DisplayName("Cliente não pode ser menor de idade")
        void naoPodeClienteMenorDeIdade(){
            LocalDate dataNascimento = LocalDate.of(2020, Month.JANUARY, 5);
            DateTimeFormatter formato =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento.format(formato);

            RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@email.com", "senha123", "12345678910", dataNascimento);

            assertThrows(IdadeException.class, () ->{
                clienteService.registrarNovoCliente(request);
            });

        }

        @Test
        @DisplayName("Cliente não pode ter CPF inválido")
        void cpfNaoPodeSerInvalido(){
            LocalDate dataNascimento = LocalDate.of(1999, Month.JANUARY, 5);
            DateTimeFormatter formato =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
            dataNascimento.format(formato);

            RequestClienteDTO request = new RequestClienteDTO("Afonso", "af@email.com", "senha123", "123456", dataNascimento);

            assertThrows(CpfInvalidoException.class, () ->{
                clienteService.registrarNovoCliente(request);
            });

        }
    }

    //helper


}
