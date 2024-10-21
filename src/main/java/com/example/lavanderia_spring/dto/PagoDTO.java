package com.example.lavanderia_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDTO {

    Integer idPedido;
    Double montoPagado;
}
