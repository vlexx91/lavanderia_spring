package com.example.lavanderia_spring;

import ch.qos.logback.core.testUtil.MockInitialContext;
import com.example.lavanderia_spring.dto.CrearPedidosDTO;
import com.example.lavanderia_spring.dto.MensajeDTO;
import com.example.lavanderia_spring.dto.PagoDTO;
import com.example.lavanderia_spring.dto.PedidosPrendasCatalogoDTO;
import com.example.lavanderia_spring.modelos.*;
import com.example.lavanderia_spring.repositorios.*;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PedidosIntegrationTest {

    @InjectMocks
    private PedidosServicio pedidosServicio;

    @Mock
    private PedidosRepositorio pedidosRepositorio;

    @Mock
    private PagosRepositorio pagosRepositorio;

    @Mock
    private ClienteRepositorio clienteRepositorio;

    @Mock
    private PedidosPrendasCatalogoRepositorio pedidosPrendasCatalogoRepositorio;

    @Mock
    private PrendasRepositorio prendasRepositorio;

    @Mock
    private CatalogoRepositorio catalogoRepositorio;


    /**
     * TEST 3 - TESTTOTAL
     */

    @Test
    void testCalcularImportePedidoExistente() {
        // GIVEN
        int pedidoId = 1;
        Pedidos pedido = new Pedidos();
        pedido.setId(pedidoId);

        List<PedidosPrendasCatalogo> detalles = new ArrayList<>();
        PedidosPrendasCatalogo detalle1 = new PedidosPrendasCatalogo();
        detalle1.setPrecio(50.0f);
        detalle1.setCantidad(2);
        detalles.add(detalle1);

        PedidosPrendasCatalogo detalle2 = new PedidosPrendasCatalogo();
        detalle2.setPrecio(30.0f);
        detalle2.setCantidad(1);
        detalles.add(detalle2);

        when(pedidosRepositorio.findById(pedidoId)).thenReturn(Optional.of(pedido));
        when(pedidosPrendasCatalogoRepositorio.findByPedidosId(pedidoId)).thenReturn(detalles);

        // WHEN
        Double total = pedidosServicio.calcularImporte(pedidoId);

        // THEN
        assertNotNull(total);
        assertEquals(130.0, total);
    }


    /**
     * TEST 4 - TESTCREAR
     */

    @Test
    public void testCrearPedido() {
        // GIVEN
        Cliente cliente = new Cliente();
        cliente.setId(1);
        cliente.setNombre("Juan Perez");
        cliente.setEmail("juan.perez@example.com");

        Pedidos pedido = new Pedidos();
        pedido.setId(1);
        pedido.setCliente(cliente);
        pedido.setFechaEntrega(LocalDate.now().plusDays(3));
        pedido.setTotalPrecio(100.0);

        Prendas prenda = new Prendas();
        prenda.setId(1);

        Catalogo catalogo = new Catalogo();
        catalogo.setId(1);

        PedidosPrendasCatalogoDTO detalleDTO = new PedidosPrendasCatalogoDTO();
        detalleDTO.setId_prendas(1);
        detalleDTO.setId_catalogo(1);
        detalleDTO.setPrecio(100.0f);
        detalleDTO.setCantidad(1);

        CrearPedidosDTO crearPedidosDTO = new CrearPedidosDTO();
        crearPedidosDTO.setClienteId(1);
        crearPedidosDTO.setTotalPrecio(100.0);
        crearPedidosDTO.setDetalles(List.of(detalleDTO));

        when(clienteRepositorio.findById(1)).thenReturn(Optional.of(cliente));
        when(pedidosRepositorio.save(Mockito.any(Pedidos.class))).thenReturn(pedido);
        when(prendasRepositorio.findById(1)).thenReturn(Optional.of(prenda));
        when(catalogoRepositorio.findById(1)).thenReturn(Optional.of(catalogo));

        // WHEN
        Pedidos pedidoCreado = pedidosServicio.crearPedido(crearPedidosDTO);

        // THEN
        assertNotNull(pedidoCreado);
        Mockito.verify(clienteRepositorio).findById(1);
        Mockito.verify(pedidosRepositorio).save(Mockito.any(Pedidos.class));
        Mockito.verify(prendasRepositorio).findById(1);
        Mockito.verify(catalogoRepositorio).findById(1);
        Mockito.verify(pedidosPrendasCatalogoRepositorio).save(Mockito.any(PedidosPrendasCatalogo.class));
    }

    /**
     * TEST 5 - TESTPAGAR
     */

    //caso pedido no encontrado
    @Test
    public void testTotalPedidoNoEncontrado(){

        //GIVEN
        PagoDTO pago1 = new PagoDTO();
        pago1.setIdPedido(1);
        pago1.setMontoPagado(100.00);

        when(pedidosRepositorio.findById(1)).thenReturn(Optional.empty());

        //WHEN & THEN
        Exception exception = assertThrows(RuntimeException.class, ()-> pedidosServicio.procesarPago(pago1));

        assertEquals("Pedido no encontrado", exception.getMessage());

    }

    //caso pago no encontrado
    @Test
    public void testTotalPagoNoEncontrado(){

        //GIVEN
        PagoDTO pago2 = new PagoDTO();
        pago2.setIdPedido(1);
        pago2.setMontoPagado(100.00);

        Pedidos pedido1 = new Pedidos();
        pedido1.setId(1);

        when(pedidosRepositorio.findById(1)).thenReturn(Optional.of(pedido1));
        when(pagosRepositorio.findByPedidoId(1)).thenReturn(null);

        //WHEN
        MensajeDTO mensaje1 = pedidosServicio.procesarPago(pago2);

        //THEN
        assertEquals("Pago no encontrado para el pedido", mensaje1.getMensaje());
    }

    //test caso pedido pago totalmente
    @Test
    public void testTotalPagado(){

        //GIVEN
        PagoDTO pago3 = new PagoDTO();
        pago3.setIdPedido(1);
        pago3.setMontoPagado(100.00);

        Pedidos pedidos2 = new Pedidos();
        pedidos2.setId(1);

        Pagos pago1 = new Pagos();
        pago1.setCantidadDebida(0.0);

        when(pedidosRepositorio.findById(1)).thenReturn(Optional.of(pedidos2));
        when(pagosRepositorio.findByPedidoId(1)).thenReturn(pago1);

        //WHEN
        MensajeDTO mensaje2 = pedidosServicio.procesarPago(pago3);

        //THEN
        assertEquals("Pedido ya está pagado", mensaje2.getMensaje());
    }

    //test caso falta dinero
    @Test
    public void testTotalFaltaDinero(){

        //GIVEN
        PagoDTO pago4 = new PagoDTO();
        pago4.setIdPedido(1);
        pago4.setMontoPagado(50.00);

        Pedidos pedidos3 = new Pedidos();
        pedidos3.setId(1);

        Pagos pagos2 = new Pagos();
        pagos2.setCantidadDebida(100.00);

        when(pedidosRepositorio.findById(1)).thenReturn(Optional.of(pedidos3));
        when(pagosRepositorio.findByPedidoId(1)).thenReturn(pagos2);

        //WHEN
        MensajeDTO mensaje3 = pedidosServicio.procesarPago(pago4);

        //THEN
        assertEquals("Pedido pagado, falta: 50.0", mensaje3.getMensaje());

    }

    //test sobra dinero
    @Test
    public void testTotalSobraDinero(){

        PagoDTO pagos5 = new PagoDTO();
        pagos5.setIdPedido(1);
        pagos5.setMontoPagado(150.00);

        Pedidos pedidos4 = new Pedidos();
        pedidos4.setId(1);

        Pagos pago3 = new Pagos();
        pago3.setCantidadDebida(100.00);

        when(pedidosRepositorio.findById(1)).thenReturn(Optional.of(pedidos4));
        when(pagosRepositorio.findByPedidoId(1)).thenReturn(pago3);

        //WHEN
        MensajeDTO mensaje4 = pedidosServicio.procesarPago(pagos5);

        //THEN
        assertEquals("Pedido pagado y sobra: 50.0", mensaje4.getMensaje());
    }




}


