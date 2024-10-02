package com.example.lavanderia_spring.servicios;


import com.example.lavanderia_spring.modelos.Prendas;
import com.example.lavanderia_spring.repositorios.PrendasRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class PrendasServicio {

    private PrendasRepositorio prendasRepositorio;


    /**
     * Extrae todos los perfiles de base de datos
     */

    public List<Prendas> getPrendas(){
        List<Prendas> prendas = prendasRepositorio.findAll();
        return prendas;
    }

    /**
     * busca un perfil a partir de su ID
     * @param id
     * @return
     */

    public Prendas getById(Integer id){
        return prendasRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un perfil nuevo o lo modifica en la base de datos
     * @param prendas
     * @return
     */

    public Prendas crearPrenda(Prendas prendas){
        return prendasRepositorio.save(prendas);
    }

    /**
     * Elimina un perfil por Id
     * @param id
     */

    public void eliminarPrendaPorId(Integer id){
        prendasRepositorio.deleteById(id);
    }


    /**
     * Elimina un perfil
     * @param prendas
     */

    public void eliminarPrenda(Prendas prendas){
        prendasRepositorio.delete(prendas);
    }
}
