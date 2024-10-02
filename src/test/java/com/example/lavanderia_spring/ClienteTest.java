package com.example.lavanderia_spring;

import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.repositorios.ClienteRepositorio;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
public class ClienteTest {
    @Autowired
    private ClienteServicio clienteServicio;

//    @Autowired
//    private ClienteRepositorio clienteRepositorio;
//
//    @Test
//    void testFindAllClients(){
//        List<Cliente> clientes = clienteRepositorio.findAll();
//        for (Cliente c : clientes){
//            System.out.println(c);
//        }
//    }
//
//    @Test
//    void testGetClienteByDni(){
//        String dni = "12345678";
//        List<Cliente> clientes = clienteRepositorio.findAllByDniEquals(dni);
//        for (Cliente c : clientes){
//            System.out.println(c);
//        }
//    }

    @Test
    void testGetClienteByDni() {
        String dni = "12345678";
        List<Cliente> clientes = clienteServicio.getCliente(dni);
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    @Test
    void testCrearCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Maikel Rich");
        cliente.setDni("12345678");
        cliente.setDireccion("Calle 321");
        cliente.setTelefono("123456789");
        cliente.setEmail("hola@gmail.com");
        cliente.setFechaNacimiento(LocalDate.of(1990, 2, 1));

        Cliente nuevoCliente = clienteServicio.crearCliente(cliente);
        System.out.println(nuevoCliente);
    }


}