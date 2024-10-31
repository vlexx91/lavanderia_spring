package com.example.lavanderia_spring.repositorios;


import com.example.lavanderia_spring.modelos.Token;
import com.example.lavanderia_spring.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Integer> {

    Token findTopByUsuario(Usuario usuario);

}
