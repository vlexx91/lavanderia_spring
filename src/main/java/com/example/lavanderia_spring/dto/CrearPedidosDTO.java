package com.example.lavanderia_spring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearPedidosDTO {

    private Integer clienteId;
    private List<PedidosPrendasCatalogoDTO> detalles;
    private Double totalPrecio;
}
