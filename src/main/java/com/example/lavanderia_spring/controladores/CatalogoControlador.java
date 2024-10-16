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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/servicio")
    public MensajeDTO servicioDisponible(@RequestParam TipoPrenda tipoPrenda, @RequestParam TipoCatalogo tipoCatalogo){
        return catalogoServicio.servicioDisponible(tipoPrenda, tipoCatalogo);
    }
}
