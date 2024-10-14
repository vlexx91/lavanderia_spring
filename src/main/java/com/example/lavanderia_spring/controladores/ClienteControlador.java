package com.example.lavanderia_spring.controladores;


import com.example.lavanderia_spring.dto.ClienteCrearDTO;
import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.repositorios.ClienteRepositorio;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor

public class ClienteControlador {


    private final ClienteRepositorio clienteRepositorio;
    private ClienteServicio clienteServicio;

    //usaremos GET, POST, PUT, DELETE
    //consulta -> getMapping

    //obtener todos los clientes
    @GetMapping("/lista")
    public List<Cliente> getAllClientes(){
        List<Cliente> clientes = clienteServicio.getClientes();
        return clientes;
    }

    //getAll con DTO
    public List<ClienteCrearDTO> getAll(){
        List<Cliente> clientes = clienteServicio.getClientes();
        List<ClienteCrearDTO> clienteDTO = new ArrayList<>();
        for (Cliente c : clientes){
            ClienteCrearDTO dto = new ClienteCrearDTO();
            dto.setNombre(c.getNombre());
            dto.setEmail(c.getEmail());
        }
        return clienteDTO;
    }

    @GetMapping("/get")
    public Cliente getClienteById(@RequestParam("id") Integer id){
        Cliente cliente = clienteServicio.getClienteById(id);
        return cliente;
    }

    //obtener cliente por id con path
    @GetMapping("/get/{id}")
    public Cliente getClienteByIdPath(@PathVariable Integer id){
        Cliente cliente = clienteServicio.getClienteById(id);
        return cliente;
    }

    //CREAR/guardar CLIENTE
    @PostMapping("/editar")
    public Cliente guardar(@RequestBody Cliente cliente) {
        cliente = clienteServicio.crearCliente(cliente);
        return cliente;
    }

    @PostMapping("/guardar")
    public Cliente guardarDTO(@RequestBody ClienteCrearDTO dto){
        Cliente cliente = clienteServicio.guardarClienteDTO(dto);
        return cliente;
    }

    @DeleteMapping("/eliminar")
    public String eliminar(@RequestParam("id") Integer id){
        try {
            clienteServicio.eliminarClientePorId(id);
            return "Cliente eliminado";
        } catch (Exception e) {
            // Manejar la excepción si ocurre algún error al eliminar el cliente
            return "Imposible eliminar cliente";
        }
    }

    //actualizar cliente
    @PutMapping()
    public Cliente editar(@RequestBody Cliente cliente,
                          @RequestParam("id") Integer id){
        cliente = clienteServicio.crearCliente(cliente);
        return cliente;
    }
}



