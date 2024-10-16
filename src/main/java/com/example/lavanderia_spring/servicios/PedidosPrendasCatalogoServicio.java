package com.example.lavanderia_spring.servicios;


import com.example.lavanderia_spring.modelos.PedidosPrendasCatalogo;
import com.example.lavanderia_spring.repositorios.PedidosPrendasCatalogoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PedidosPrendasCatalogoServicio {

    private PedidosPrendasCatalogoRepositorio pedidosPrendasCatalogoRepositorio;


    /**
     * Extrae todos los pedidos de la base de datos
     */

//    public List<PedidosPrendasCatalogoDTO> getAll() {
//        List<PedidosPrendasCatalogo> entities = pedidosPrendasCatalogoRepositorio.findAll();
//        return entities.stream().map(PedidosPrendasCatalogoDTO::fromEntity).toList();
//    }



    /**
     * busca un pedido a partir de su ID
     * @param id
     * @return
     */

    public PedidosPrendasCatalogo getById(Integer id){
        return pedidosPrendasCatalogoRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un pedido nuevo o lo modifica en la base de datos
     * @param pedidosPrendasCatalogo
     * @return
     */

    public PedidosPrendasCatalogo crearPedidosPrendasCatalogo (PedidosPrendasCatalogo pedidosPrendasCatalogo){
        return pedidosPrendasCatalogoRepositorio.save(pedidosPrendasCatalogo);
    }


    /**
     * Elimina un pedido por Id
     * @param id
     */

    public void eliminarPedidosPrendasCatalogoPorId (Integer id){
        pedidosPrendasCatalogoRepositorio.deleteById(id);
    }

    /**
     * Elimina un pedido
     * @param pedidosPrendasCatalogo
     */

    public void eliminar(PedidosPrendasCatalogo pedidosPrendasCatalogo){
        pedidosPrendasCatalogoRepositorio.delete(pedidosPrendasCatalogo);
    }
}
