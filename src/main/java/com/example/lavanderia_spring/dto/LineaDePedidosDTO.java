package com.example.lavanderia_spring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LineaDePedidosDTO {

    private Integer idPrendas;
    private Integer idCatalogo;
    private Integer cantidad;
    private Float precio;
}
