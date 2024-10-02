package com.example.lavanderia_spring.repositorios;

import com.example.lavanderia_spring.modelos.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagosRepositorio extends JpaRepository<Pagos, Integer> {

}
