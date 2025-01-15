package com.example.lavanderia_spring;

import com.example.lavanderia_spring.dto.CrearPedidosDTO;
import com.example.lavanderia_spring.dto.PagoDTO;
import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.modelos.Pagos;
import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.modelos.PedidosPrendasCatalogo;
import com.example.lavanderia_spring.repositorios.ClienteRepositorio;
import com.example.lavanderia_spring.repositorios.PagosRepositorio;
import com.example.lavanderia_spring.repositorios.PedidosRepositorio;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import com.example.lavanderia_spring.servicios.PagosServicio;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
public class PedidosTest {

    @Autowired
    private PedidosServicio pedidosServicio;

    @Autowired
    private PedidosRepositorio pedidosRepositorio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PagosServicio pagosServicio;

    @Autowired
    private PagosRepositorio pagosRepositorio;

    @BeforeEach
    public void inicializarDatos(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan Perez");
        cliente.setEmail("juan.perez@example.com");
        clienteRepositorio.save(cliente);

        Cliente clienteGuardado = clienteRepositorio.findById(cliente.getId()).orElseThrow();

        Pedidos p1 = new Pedidos();
        p1.setCliente(clienteGuardado);
        p1.setFechaEntrega(LocalDate.now().plusDays(3));
        p1.setTotalPrecio(100.0);
        p1.setPedidosPrendasCatalogos(new ArrayList<>());

        PedidosPrendasCatalogo item = new PedidosPrendasCatalogo();
        item.setId_pedidos(p1);
        item.setPrecio(100.0f);
        item.setCantidad(1);
        p1.setPedidosPrendasCatalogos(List.of(item));

        pedidosRepositorio.save(p1);
    }

    /**
     * TEST 3 - TESTTOTALPOSITIVO
     */

    @Test
    void testTotalPositivo(){

        //GIVEN
        List<PedidosPrendasCatalogo> listaPrecio = new ArrayList<>();

        //WHEN
        Double total = pedidosServicio.calcularImporte(1);

        //THEN
        assertNotNull(total);
        assertEquals(100.0, total);
    }

    /**
     * TEST 3 - TESTTOTALNEGATIVO
     */

    @Test
    void testTotalNegativo(){

        // WHEN
        Exception exception = assertThrows(RuntimeException.class, () -> pedidosServicio.calcularImporte(99));

        // THEN
        assertEquals("No se encuentra el pedido", exception.getMessage());
    }

    /**
     * TEST 4 - TESTCREARPOSITIVO
     */

    @Test
    void testCrearPositivo(){
        Cliente cliente = new Cliente();
        cliente.setNombre("Joselito Pere");
        cliente.setEmail("joselito.pere@example.com");
        clienteRepositorio.save(cliente);

        Cliente clienteGuardado = clienteRepositorio.findById(cliente.getId()).orElseThrow();

        Pedidos p1 = new Pedidos();
        p1.setCliente(clienteGuardado);
        p1.setFechaEntrega(LocalDate.now().plusDays(5));
        p1.setTotalPrecio(50.0);
        p1.setPedidosPrendasCatalogos(new ArrayList<>());

        PedidosPrendasCatalogo item = new PedidosPrendasCatalogo();
        item.setId_pedidos(p1);
        item.setPrecio(50.0f);
        item.setCantidad(2);
        p1.setPedidosPrendasCatalogos(List.of(item));

        pedidosRepositorio.save(p1);
    }

    /**
     * TEST 4- TESTCREARNEGATIVO
     */

    @Test
    void testCrearNegativo(){

        //GIVEN
        CrearPedidosDTO crearPedidosDTO = new CrearPedidosDTO();

        crearPedidosDTO.setClienteId(99);
        crearPedidosDTO.setTotalPrecio(80.00);
        crearPedidosDTO.setDetalles(new ArrayList<>());

        //WHEN && THEN
        Exception exception = assertThrows(RuntimeException.class, ()-> pedidosServicio.crearPedido(crearPedidosDTO));

        assertEquals("Cliente no encontrado", exception.getMessage());
    }

    /**
     * TEST 5 - TESTPAGARPOSITIVO
     */

    @Test
    void testPagarPositivo(){

        PagoDTO pago = new PagoDTO();
        pago.setIdPedido(1);
        pago.setMontoPagado(100.00);

        Pagos pagoEntity = new Pagos();
        pagoEntity.setId(pago.getIdPedido());
        pagoEntity.setPagado(true);
        pagoEntity.setCantidadDebida(pago.getMontoPagado());

        pagosRepositorio.save(pagoEntity);
    }

    /**
     * TEST 5 - TESTPAGARNEGATIVO
     */
    @Test
    void testPagarNegativo(){

        //GIVEN
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setIdPedido(99);
        pagoDTO.setMontoPagado(100.00);

        // WHEN & THEN
        Exception exception = assertThrows(RuntimeException.class, () -> {
            pedidosServicio.procesarPago(pagoDTO);
        });

        // Verificar que el mensaje de la excepción es el esperado
        assertEquals("Pedido no encontrado", exception.getMessage());
    }


}
