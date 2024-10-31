package com.example.lavanderia_spring.repositorios;

import com.example.lavanderia_spring.modelos.Pagos;
import com.example.lavanderia_spring.modelos.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepositorio extends JpaRepository<Pagos, Integer> {

    @Query("SELECT p FROM Pagos p WHERE p.pedidos.id = :pedidoId")
    Pagos findByPedidoId(@Param("pedidoId") Integer pedidoId);


    //consulta para probar si el catalogo tiene pago
    @Query("SELECT COUNT(p) > 0 FROM Pagos p JOIN p.pedidos ped JOIN ped.pedidosPrendasCatalogos ppc WHERE ppc.id_pedidos.id = :id AND p.pagado != true")
    boolean existe(Integer id);
}
