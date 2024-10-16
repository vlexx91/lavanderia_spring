package com.example.lavanderia_spring.servicios;


import com.example.lavanderia_spring.dto.CatalogoDTO;
import com.example.lavanderia_spring.dto.MensajeDTO;
import com.example.lavanderia_spring.enumerados.TipoCatalogo;
import com.example.lavanderia_spring.enumerados.TipoPrenda;
import com.example.lavanderia_spring.modelos.Catalogo;
import com.example.lavanderia_spring.repositorios.CatalogoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<CatalogoDTO> getAllDTO(){
        List<Catalogo> catalogo = catalogoRepositorio.findAll();
        List<CatalogoDTO> catalogoDTO = new ArrayList<>();
        for(Catalogo c : catalogo){
            CatalogoDTO catalogoDTO1 = new CatalogoDTO();
            catalogoDTO1.setPrecio(c.getPrecioServPrenda());
            catalogoDTO1.setId_catalogo(c.getTipoCatalogo());
            catalogoDTO1.setId_prendas(c.getTipoPrenda());
            catalogoDTO.add(catalogoDTO1);
        }
        return catalogoDTO;
    }

    public MensajeDTO servicioDisponible(TipoPrenda tipoPrenda, TipoCatalogo tipoCatalogo){

        MensajeDTO mensaje = new MensajeDTO();

        //compruebo si el tipo de prenda y el tipo de catologo estan en mi base de datos
        //saco todos mis catalgoos con findAll
        List<Catalogo> catalogo = catalogoRepositorio.findAll();

        //recorro todos los catalogos
        for (Catalogo c : catalogo){
            //compruebo el tipo de prenda y tipo catalogo esta dentro de los que existen
            if (c.getTipoPrenda().equals(tipoPrenda)&&c.getTipoCatalogo().equals(tipoCatalogo)){
                mensaje.setMensaje("Servicio disponible");
                return mensaje;
            }
        }
        mensaje.setMensaje("No disponible");
        return mensaje;
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
