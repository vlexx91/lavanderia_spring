package com.example.lavanderia_spring.repositorios;

import com.example.lavanderia_spring.modelos.Cliente;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {

    List<Cliente> findAllByDniEquals(String dni);
}
