package com.example.lavanderia_spring.servicios;

import com.example.lavanderia_spring.dto.ClienteCrearDTO;
import com.example.lavanderia_spring.mappers.ClienteMapper;
import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service //NO OLVIDAR
@AllArgsConstructor
public class ClienteServicio {


    /**
     * Busca clientes por DNI
     *
     * @param dni
     * return
     */

    private ClienteRepositorio clienteRepositorio;


    public List<Cliente> getCliente(String dni) {
        List<Cliente> cliente = clienteRepositorio.findAllByDniEquals(dni);
        return cliente;
    }

    /**
     * Busca un cliente por ID
     *
     * @param id
     * @return
     */

    public Cliente getClienteById(Integer id) {
        return clienteRepositorio.findById(id).orElse(null);
    }

    /**
     * Buscar todos los clientes
     *
     * @return
     */

    public List<Cliente> getClientes() {
        List<Cliente> cliente = clienteRepositorio.findAll();
        return cliente;

       }

    /**
     * Crear un cliente nuevo o modificar un existente
     */

    public Cliente crearCliente (Cliente cliente) {
        return clienteRepositorio.save(cliente);
   }

   /**
   * guardar cliente DTO
   */

   public Cliente guardarClienteDTO(ClienteCrearDTO dto){

           Cliente clienteGuardar = new Cliente();
           clienteGuardar.setNombre(dto.getNombre());
           clienteGuardar.setEmail(dto.getEmail());
           clienteGuardar.setDireccion(dto.getDireccion());
           clienteGuardar.setDni(dto.getDni());
           clienteGuardar.setTelefono(dto.getTelefono());

           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
           LocalDate fechaNacimiento = LocalDate.parse(dto.getFechaNacimiento(), formatter);
           clienteGuardar.setFechaNacimiento(fechaNacimiento);

           //for (Cliente c : clientes){
               //clienteMapper.add(c); //ESTO ESTA INCOMPLETO MIRAR PROYECTO LUIS
           //}

           return clienteRepositorio.save(clienteGuardar);
   }

    /**
     * Eliminar un cliente
     */

    public void eliminarCliente(Cliente cliente) {
       clienteRepositorio.delete(cliente);
   }

    /**
     * Elimina un cliente por Id
     */

    public void eliminarClientePorId(Integer id) {
        clienteRepositorio.deleteById(id);
    }
}
