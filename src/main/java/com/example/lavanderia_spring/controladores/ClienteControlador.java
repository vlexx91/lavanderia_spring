package com.example.lavanderia_spring.controladores;


import com.example.lavanderia_spring.dto.ClienteDTO;
import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor

public class ClienteControlador {


    private ClienteServicio clienteServicio;

    //usaremos GET, POST, PUT, DELETE
    //consulta -> getMapping
    @GetMapping("/lista")
    public List<Cliente> getAllClientes(){
        List<Cliente> clientes = clienteServicio.getClientes();
        return clientes;
    }

    //getAll con DTO
    public List<ClienteDTO> getAll(){
        List<Cliente> clientes = clienteServicio.getClientes();
        List<ClienteDTO> clienteDTO = new ArrayList<>();
        for (Cliente c : clientes){
            ClienteDTO dto = new ClienteDTO();
            dto.setNombre(c.getNombre());
            dto.setEmail(c.getEmail());
        }
        return clienteDTO;
    }

    @GetMapping("/id/{id}")
    public Cliente getClienteByIdPath(@PathVariable Integer id){
        Cliente cliente = clienteServicio.getClienteById(id);
        return cliente;
    }

    @PostMapping()
    public Cliente guardar(@RequestBody Cliente cliente){
        cliente = clienteServicio.crearCliente(cliente);
        return cliente;
    }

    @DeleteMapping()
    public String eliminar(@RequestParam("id") Integer id){
        clienteServicio.eliminarClientePorId(id);
        return "Cliente eliminado";
    }
}



