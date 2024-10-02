package com.example.lavanderia_spring.repositorios;

import com.example.lavanderia_spring.modelos.Prendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrendasRepositorio extends JpaRepository<Prendas, Integer> {
}
