package com.example.lavanderia_spring.controladores;

import com.example.lavanderia_spring.dto.CatalogoDTO;
import com.example.lavanderia_spring.dto.MensajeDTO;
import com.example.lavanderia_spring.enumerados.TipoCatalogo;
import com.example.lavanderia_spring.enumerados.TipoPrenda;
import com.example.lavanderia_spring.modelos.Catalogo;
import com.example.lavanderia_spring.servicios.CatalogoServicio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogo")
@AllArgsConstructor
public class CatalogoControlador {

    private CatalogoServicio catalogoServicio;

    @GetMapping("/lista")
    public List<CatalogoDTO> getAllCatalogo(){
        return catalogoServicio.getAllDTO();
    }

    //Controlador para test
    @PostMapping("/verificar")
    public MensajeDTO verificarListaVacia(@RequestBody List<Catalogo> catalogos) {
        return catalogoServicio.verificarListaVacia(catalogos);
    }


    @GetMapping("/servicio")
    public MensajeDTO servicioDisponible(@RequestParam TipoPrenda tipoPrenda, @RequestParam TipoCatalogo tipoCatalogo){
        return catalogoServicio.servicioDisponible(tipoPrenda, tipoCatalogo);
    }

    @DeleteMapping("/eliminar/{id}")
    public MensajeDTO eliminar(@PathVariable Integer id) {
        return catalogoServicio.eliminarServicio(id);
    }

}
