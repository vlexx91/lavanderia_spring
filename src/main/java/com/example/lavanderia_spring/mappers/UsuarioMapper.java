package com.example.lavanderia_spring.mappers;

import com.example.lavanderia_spring.dto.UsuarioDTO;
import com.example.lavanderia_spring.modelos.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

    UsuarioDTO toDTO(Usuario entity);

    List<Usuario> toEntity(List<UsuarioDTO> dtos);

    List<UsuarioDTO> toDTO(List<Usuario> entities);



}
