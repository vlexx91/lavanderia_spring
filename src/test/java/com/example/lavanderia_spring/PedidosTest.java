package com.example.lavanderia_spring;

import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.repositorios.PedidosRepositorio;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PedidosTest {

    @Autowired
    private PedidosServicio pedidosServicio;

    @Test
    void testGetAllPedidos(){
        List<Pedidos> pedidos = pedidosServicio.getAll();
        for (Pedidos p : pedidos){
            System.out.println(p);
        }
    }
}
