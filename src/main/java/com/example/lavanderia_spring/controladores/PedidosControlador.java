package com.example.lavanderia_spring.controladores;


import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.repositorios.PedidosRepositorio;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor

public class PedidosControlador {

    private final PedidosRepositorio pedidosRepositorio;
    private PedidosServicio pedidosServicio;

    @GetMapping("/total")
    public List<Pedidos> getAllPedidos(){
        List<Pedidos> pedidos = pedidosServicio.getAll();
        return pedidos;
    }

    @GetMapping("/get")
    public Pedidos getPedidosById(@RequestParam("id") Integer id){
        Pedidos pedidos = pedidosServicio.getById(id);
        return pedidos;
    }

    @GetMapping("/get/{id}")
    public Pedidos getPedidosByIdPath(@PathVariable Integer id){
        Pedidos pedidos = pedidosServicio.getById(id);
        return pedidos;
    }



}
