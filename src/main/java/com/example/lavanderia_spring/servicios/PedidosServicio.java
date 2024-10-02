package com.example.lavanderia_spring.servicios;


import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.repositorios.PedidosRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidosServicio {

    private PedidosRepositorio pedidosRepositorio;


    /**
     * Este extrae todos los pedidos la de base de datos
     */

    public List<Pedidos> getAll(){
        return pedidosRepositorio.findAll();
    }

    /**
     * busca un pedido a partir de su ID
     * @param id
     * @return
     */

    public Pedidos getById(Integer id){
        return pedidosRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un pedido nuevo o lo modifica en la base de datos
     * @param pedidos
     * @return
     */

    public Pedidos crearPedido(Pedidos pedidos){
        return pedidosRepositorio.save(pedidos);
    }

    /**
     * Elimina un pedido por Id
     * @param id
     */

    public void eliminarPorId(Integer id){
        pedidosRepositorio.deleteById(id);
    }


    /**
     * Elimina un pedido
     * @param pedidos
     */

    public void eliminar(Pedidos pedidos){
        pedidosRepositorio.delete(pedidos);
    }

}
