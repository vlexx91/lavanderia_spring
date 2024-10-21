package com.example.lavanderia_spring.servicios;

import com.example.lavanderia_spring.dto.*;
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

    private CatalogoRepositorio catalogoRepositorio;
    private PedidosPrendasCatalogoRepositorio pedidosPrendasCatalogoRepositorio;
    private PedidosRepositorio pedidosRepositorio;
    private ClienteRepositorio clientesRepositorio;
    private PrendasRepositorio prendasRepositorio;
    private PagosRepositorio pagosRepositorio;




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

        List<PedidosPrendasCatalogo> detalles = pedidosPrendasCatalogoRepositorio.findByPedidosId(pedidoId);

        Double totalImporte = 0.0;

        for (PedidosPrendasCatalogo detalle: detalles){
            Float precio = detalle.getPrecio();
            Integer cantidad = detalle.getCantidad();

            totalImporte += precio * cantidad;
        }
        return totalImporte;
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
     * Métod0 para procesar pago
     */

    public MensajeDTO procesarPago(PagoDTO pagoDTO){
        Pedidos pedido = pedidosRepositorio.findById(pagoDTO.getIdPedido()).orElse(null);
        MensajeDTO mensaje = new MensajeDTO();
        //hago consulta

        Pagos pago = pagosRepositorio.findByPedidoId(pedido);
        //tengo que comprobar lo que queda por pagar

        Double total = pago.getCantidadDebida() - pagoDTO.getMontoPagado();

        //consulta primera si pedido esta pagado
        if (pago.getCantidadDebida()== 0){
            mensaje.setMensaje("Pedido ya está pagado");
            return mensaje;
        //consulta si falta dinero
        } else if (total > 0) {
            mensaje.setMensaje("Pedido pagado falta: " + total);
            pago.setCantidadDebida(total.floatValue());
            //tengo que guardar variable pago para que se actualice la tabla
            pagosRepositorio.save(pago);
            return mensaje;
        } else {
            //el -1 es por si el cliente paga demás mostrarle la sobra
            mensaje.setMensaje("Pedido pagado y sobra: " + total*-1);
            pago.setCantidadDebida(0.0f);
            pagosRepositorio.save(pago);
            return mensaje;

        }

    }


    //comprobar si el servicio existe
    //si servicio esta vinculado a un pedido cuyo pago es el estado no es pagado te devuelve un mensaje
    //de que no se ha podido eliminar

    //si no cumple lo anterior elimina tod0


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
