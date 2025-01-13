package com.example.lavanderia_spring;

import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.repositorios.ClienteRepositorio;
import com.example.lavanderia_spring.repositorios.PedidosRepositorio;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureTestDatabase
public class PedidosTest {

    @Autowired
    private PedidosServicio pedidosServicio;

    @Autowired
    private PedidosRepositorio pedidosRepositorio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    public void inicializarDatos(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setEmail("juan.perez@example.com");
        clienteRepositorio.save(cliente);

        Pedidos p1 = new Pedidos();
        p1.setCliente(cliente);
        p1.setFechaEntrega(LocalDate.now().plusDays(3));
        p1.setTotalPrecio(100.0);
        p1.setPedidosPrendasCatalogos(new ArrayList<>());

        pedidosRepositorio.save(p1);
    }

    /**
     * TEST 3 - TESTTOTALPOSITIVO
     */

    @Test
    void testTotalPositivo(){

    }
}
