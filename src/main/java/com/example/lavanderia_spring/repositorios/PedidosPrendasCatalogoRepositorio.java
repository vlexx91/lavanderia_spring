package com.example.lavanderia_spring.repositorios;

import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.modelos.PedidosPrendasCatalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PedidosPrendasCatalogoRepositorio extends JpaRepository<PedidosPrendasCatalogo, Integer> {
    @Query("SELECT p FROM PedidosPrendasCatalogo p WHERE p.id_pedidos.id = :pedidosId")
    List<PedidosPrendasCatalogo> findByPedidosId(@Param("pedidosId") Integer pedidosId);

}
