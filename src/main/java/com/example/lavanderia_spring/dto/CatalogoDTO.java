package com.example.lavanderia_spring.dto;


import com.example.lavanderia_spring.enumerados.TipoCatalogo;
import com.example.lavanderia_spring.enumerados.TipoPrenda;
import com.example.lavanderia_spring.modelos.Catalogo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoDTO {

    private Double precio;
    private TipoPrenda id_prendas;
    private TipoCatalogo id_catalogo;

}
