package com.example.lavanderia_spring.repositorios;

import com.example.lavanderia_spring.modelos.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepositorio extends JpaRepository<Pedidos, Integer> {
}
