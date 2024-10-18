package com.example.lavanderia_spring;

import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.repositorios.PedidosRepositorio;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
public class PedidosTest {

    @Autowired
    private PedidosServicio pedidosServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Test
    void testGetAllPedidos(){
        List<Pedidos> pedidos = pedidosServicio.getAll();
        for (Pedidos p : pedidos){
            System.out.println(p);
        }
    }

//    @Test
//    @Transactional
//    void testCrearPedidos(){
//
////        Cliente cliente = new Cliente();
////        Cliente cliente = clienteServicio.getClienteById(1);
////        Cliente clienteGuardado = clienteServicio.crearCliente(cliente);
//
//        Cliente cliente = new Cliente();
//        cliente.setNombre("Maikel Rich");
//        cliente.setDni("12345678");
//        cliente.setDireccion("Calle 321");
//        cliente.setTelefono("123456789");
//        cliente.setEmail("a@gmail.com");
//        cliente.setFechaNacimiento(LocalDate.of(1990, 2, 1));
//        Cliente clienteGuardado = clienteServicio.crearCliente(cliente);
//
//        Pedidos pedidos = new Pedidos();
//        pedidos.setFechaEntrega(LocalDate.of(2020, 2, 10));
//        pedidos.setTotalPrecio(100.0);
//        pedidos.setCliente(clienteGuardado);
//        Pedidos pedidoGuardado = pedidosServicio.crearPedido(pedidos);
//        System.out.println(pedidoGuardado);
//    }
}
