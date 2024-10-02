package com.example.lavanderia_spring.servicios;


import com.example.lavanderia_spring.modelos.Catalogo;
import com.example.lavanderia_spring.repositorios.CatalogoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CatalogoServicio {

    private CatalogoRepositorio catalogoRepositorio;


    /**
     * Extraigo todos los catalogos de la base de datos
     *
     * @return
     */

    public List<Catalogo> getAll() {
        return catalogoRepositorio.findAll();
    }

    /**
     * Busca un catalogo a partir de su ID
     *
     * @param id
     * @return
     */

    public Catalogo getById(Integer id) {
        return catalogoRepositorio.findById(id).orElse(null);
    }

    /**
     * Guarda un catalogo nuevo o lo modifica en la base de datos
     *
     * @param catalogo
     * @return
     */

    public Catalogo crearCatalogo(Catalogo catalogo){
        return catalogoRepositorio.save(catalogo);
    }

    /**
     * Elimina un catalogo
     *
     * @param catalogo
     */

    public void eliminarCatalogo (Catalogo catalogo){
        catalogoRepositorio.delete(catalogo);
    }

    /**
     * Elimina un catalogo por ID
     *
     * @param id
     */
    public void eliminarCatalogoPorId(Integer id){
        catalogoRepositorio.deleteById(id);
    }
}
