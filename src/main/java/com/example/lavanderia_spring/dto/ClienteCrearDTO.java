package com.example.lavanderia_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCrearDTO {
    
    private String nombre;
    private String email;
    private String direccion;
    private String fechaNacimiento;
    private String dni;
    private String telefono;
}
