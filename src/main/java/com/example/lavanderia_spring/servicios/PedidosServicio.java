package com.example.lavanderia_spring.servicios;

import com.example.lavanderia_spring.dto.CrearPedidosDTO;
import com.example.lavanderia_spring.dto.LineaDePedidosDTO;
import com.example.lavanderia_spring.dto.PedidosDTO;
import com.example.lavanderia_spring.dto.PedidosPrendasCatalogoDTO;
import com.example.lavanderia_spring.enumerados.TipoCatalogo;
import com.example.lavanderia_spring.modelos.*;
import com.example.lavanderia_spring.repositorios.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidosServicio {

    private final CatalogoRepositorio catalogoRepositorio;
    private final PedidosPrendasCatalogoRepositorio pedidosPrendasCatalogoRepositorio;
    private PedidosRepositorio pedidosRepositorio;
    private ClienteRepositorio clientesRepositorio;
    private PrendasRepositorio prendasRepositorio;




    /**
     * Este extrae todos los pedidos la de base de datos
     */

    public List<Pedidos> getAll(){
        return pedidosRepositorio.findAll();
    }

    /**
     * busca un pedido a partir de su ID
     * @param id
     * @return
     */

    public Pedidos getById(Integer id){
        return pedidosRepositorio.findById(id).orElse(null);
    }

    /**
     * calcular importe
     * AQUÍ LLAMO A TOTAL PRECIO DE PEDIDO, PREGUNTAR A LUIS
     */

    public Double calcularImporte (Integer pedidoId){
        Pedidos pedido = pedidosRepositorio.findById(pedidoId).orElseThrow(()-> new RuntimeException("No se encuentra el pedido"));
        return pedido.getTotalPrecio();
    }



    /**
     * Crea un pedido DTO ejercicio Luis
     */

    public Pedidos crearPedido(CrearPedidosDTO crearPedidosDTO) {
        Cliente cliente = clientesRepositorio.findById(crearPedidosDTO.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Crear el pedido sin las prendas y servicios directamente asociados
        Pedidos pedido = new Pedidos();
        pedido.setCliente(cliente);
        pedido.setTotalPrecio(crearPedidosDTO.getTotalPrecio());
        pedido.setFechaEntrega(LocalDate.now());
        Pedidos pedidoGuardado = pedidosRepositorio.save(pedido);

        // Crear las relaciones en PedidosPrendasCatalogo
        for (PedidosPrendasCatalogoDTO detalle : crearPedidosDTO.getDetalles()) {
            Prendas prenda = prendasRepositorio.findById(detalle.getId_prendas())
                    .orElseThrow(() -> new RuntimeException("Prenda no encontrada"));

            Catalogo catalogo = catalogoRepositorio.findById(detalle.getId_catalogo())
                    .orElseThrow(() -> new RuntimeException("Catalogo no encontrado"));

            PedidosPrendasCatalogo pedidosPrendasCatalogo = new PedidosPrendasCatalogo();
            pedidosPrendasCatalogo.setId_pedidos(pedidoGuardado);
            pedidosPrendasCatalogo.setId_prendas(prenda);
            pedidosPrendasCatalogo.setId_catalogo(catalogo);
            pedidosPrendasCatalogo.setPrecio(detalle.getPrecio());
            pedidosPrendasCatalogo.setCantidad(detalle.getCantidad());

            pedidosPrendasCatalogoRepositorio.save(pedidosPrendasCatalogo);
        }

        return pedidoGuardado;
    }


    /**
     * Elimina un pedido por Id
     * @param id
     */

    public void eliminarPorId(Integer id){
        pedidosRepositorio.deleteById(id);
    }


    /**
     * Elimina un pedido
     * @param pedidos
     */

    public void eliminar(Pedidos pedidos){
        pedidosRepositorio.delete(pedidos);
    }



}
