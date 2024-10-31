package com.example.lavanderia_spring.dto;

import com.example.lavanderia_spring.enumerados.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Integer id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Rol rol;
}
