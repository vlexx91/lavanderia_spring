package com.example.lavanderia_spring.servicios;

import com.example.lavanderia_spring.modelos.Pagos;
import com.example.lavanderia_spring.repositorios.PagosRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PagosServicio {

    private PagosRepositorio pagosRepositorio;

    /**
     * Este extrae todos los perfiles de base de datos
     */

    public List<Pagos> getAll(){
        return pagosRepositorio.findAll();
    }

    /**
     * busca un perfil a partir de su ID
     * @param id
     * @return
     */
    public Pagos getById(Integer id){
        return pagosRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un perfil nuevo o lo modifica en la base de datos
     * @param pagos
     * @return
     */

    public Pagos save(Pagos pagos){
        return pagosRepositorio.save(pagos);
    }


    /**
     * Elimina un perfil por Id
     * @param id
     */

    public void eliminarPorId(Integer id){
        pagosRepositorio.deleteById(id);
    }


    /**
     * Elimina un perfil
     * @param pagos
     */

    public void eliminar(Pagos pagos){
        pagosRepositorio.delete(pagos);
    }

}
