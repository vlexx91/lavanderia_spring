package com.example.lavanderia_spring.servicios;

import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.repositorios.ClienteRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
