package com.example.lavanderia_spring.repositorios;

import com.example.lavanderia_spring.modelos.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoRepositorio extends JpaRepository<Catalogo, Integer> {

}
