package com.commerce.agile.cliente;

import com.commerce.agile.dto.cliente.RequestClienteDTO;
import com.commerce.agile.dto.cliente.ResponseClienteDTO;
import com.commerce.agile.service.ClienteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ClienteTest {

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


    }
    @Nested
    class IncorrectProcedureTest{

    }

    //helper


}
