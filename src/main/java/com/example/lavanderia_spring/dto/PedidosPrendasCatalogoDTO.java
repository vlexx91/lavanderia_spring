package com.example.lavanderia_spring.dto;


import com.example.lavanderia_spring.modelos.PedidosPrendasCatalogo;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidosPrendasCatalogoDTO {

    private Integer id;
    private Float precio;
    private Integer id_prendas;
    private Integer id_catalogo;
    private Integer cantidad;

}
