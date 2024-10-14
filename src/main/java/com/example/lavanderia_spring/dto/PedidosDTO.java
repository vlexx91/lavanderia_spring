package com.example.lavanderia_spring.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PedidosDTO {

    private Integer id;
    private LocalDate fechaEntrega;
    private Double totalPrecio;
    private List<LineaDePedidosDTO> lineaDePedidosDTOS;
}
