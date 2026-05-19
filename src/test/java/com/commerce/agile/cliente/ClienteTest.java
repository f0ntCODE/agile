package com.commerce.agile.cliente;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

            RequestClienteDTO dto = new ResponseClienteDTO("Afonso", "af@email.com", "senha123", dataNascimento);

            ResponseClienteDTO cliente = clienteService.registrarNovoCliente(dto);

            assertTrue(clienteService.buscarPeloId(cliente.id).isPresent());

        }


    }
    @Nested
    class IncorrectProcedureTest{

    }

    //helper


}
