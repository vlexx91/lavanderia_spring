package com.example.lavanderia_spring;

import com.example.lavanderia_spring.modelos.*;
import com.example.lavanderia_spring.repositorios.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LavanderiaSpringApplicationTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidosRepositorio pedidosRepositorio;

    @Autowired
    private CatalogoRepositorio catalogoRepositorio;

    @Autowired
    private PagosRepositorio pagosRepositorio;

    @Autowired
    private PrendasRepositorio prendasRepositorio;

    @Autowired
    private PedidosPrendasCatalogoRepositorio pedidosPrendasCatalogoRepositorio;



    @Test
    void testFindAllClients(){
        List<Cliente> clientes = clienteRepositorio.findAll();
        for (Cliente cliente : clientes){
            System.out.println(cliente);
        }
    }

    @Test
    void testFindAllPedidos(){
        List<Pedidos> pedidos = pedidosRepositorio.findAll();
        for (Pedidos pedido : pedidos){
            System.out.println(pedido);
        }
    }

    @Test
    void testFindAllCatalogo(){
        List<Catalogo> catalogos = catalogoRepositorio.findAll();
        for (Catalogo catalogo : catalogos){
            System.out.println(catalogo);
        }
    }

    @Test
    void testFindAllPagos(){
        List<Pagos> pagos = pagosRepositorio.findAll();
        for (Pagos pago : pagos){
            System.out.println(pago);
        }
    }

    @Test
    void testFindAllPrendas(){
        List<Prendas> prendas = prendasRepositorio.findAll();
        for (Prendas prenda : prendas){
            System.out.println(prenda);
        }
    }

    @Test
    void testFindAllPedidosPrendasCatalogo(){
        List<PedidosPrendasCatalogo> pedidosPrendasCatalogos = pedidosPrendasCatalogoRepositorio.findAll();
        for (PedidosPrendasCatalogo pedidosPrendasCatalogo : pedidosPrendasCatalogos){
            System.out.println(pedidosPrendasCatalogo);
        }
    }

}
