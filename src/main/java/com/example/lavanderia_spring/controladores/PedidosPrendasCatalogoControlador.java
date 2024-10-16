package com.example.lavanderia_spring.controladores;



import com.example.lavanderia_spring.modelos.PedidosPrendasCatalogo;
import com.example.lavanderia_spring.repositorios.PedidosPrendasCatalogoRepositorio;
import com.example.lavanderia_spring.servicios.PedidosPrendasCatalogoServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/precios")
@AllArgsConstructor
public class PedidosPrendasCatalogoControlador {

    private final PedidosPrendasCatalogoRepositorio pedidosPrendasCatalogoRepositorio;
    private PedidosPrendasCatalogoServicio pedidosPrendasCatalogoServicio;

//    @GetMapping("/lavado")
//    public List<PedidosPrendasCatalogoDTO> getAll(){
//        return pedidosPrendasCatalogoServicio.getAll();
//    }


}
