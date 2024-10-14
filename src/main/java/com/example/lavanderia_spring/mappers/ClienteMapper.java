package com.example.lavanderia_spring.mappers;

import com.example.lavanderia_spring.dto.ClienteCrearDTO;
import com.example.lavanderia_spring.modelos.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClienteMapper {

    /**
     * Este mét0do sirve para pasar a Entity un DTO
     * @param dto
     * @return
     */

    //Cliente toEntity(ClienteCrearDTO dto);

    /**
     * Este mét0do sirve para pasar a DTO una Entity
     * @param entity
     * @return
     */

//    ClienteCrearDTO toDTO(Cliente entity);
//
//    List<Cliente> toEntity(List<ClienteCrearDTO> dtos);
//    List<Cliente> toDTO(List<Cliente> entities);
}
