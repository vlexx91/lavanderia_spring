package com.example.lavanderia_spring;


import com.example.lavanderia_spring.dto.CatalogoDTO;
import com.example.lavanderia_spring.dto.MensajeDTO;
import com.example.lavanderia_spring.enumerados.TipoCatalogo;
import com.example.lavanderia_spring.enumerados.TipoPrenda;
import com.example.lavanderia_spring.modelos.Catalogo;
import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.repositorios.CatalogoRepositorio;
import com.example.lavanderia_spring.servicios.CatalogoServicio;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
//@Transactional
public class CatalogoTest {

    @Autowired
    private CatalogoRepositorio catalogoRepositorio;

    @Autowired
    private CatalogoServicio catalogoServicio;

    @BeforeEach
    public void inicializarDatos() {

        Catalogo c1 = new Catalogo();
        c1.setTipoCatalogo(TipoCatalogo.LAVADO_PLANCHADO);
        c1.setTipoPrenda(TipoPrenda.BLUSA);
        c1.setPrecioServPrenda(50.00);

        catalogoRepositorio.save(c1);

        Catalogo c2 = new Catalogo();
        c2.setTipoCatalogo(TipoCatalogo.LAVADO);
        c2.setTipoPrenda(TipoPrenda.CAMISA);
        c2.setPrecioServPrenda(30.00);

        catalogoRepositorio.save(c2);

    }

    /**
     * TEST 1 - TESTLISTAPOSITIVO
     */

    @Test
    void testGetAllCatalogo() {
        //GIVEN
        //WHEN
        List<CatalogoDTO> catalogo = catalogoServicio.getAllDTO();
        //THEN
        assertEquals(2, catalogo.size());
    }

    /**
     * TEST 1 - TESTLISTANEGATIVO
     */

    @Test
    void testVerificarListaVacia() {
        List<Catalogo> catalogosVacios = new ArrayList<>();
        MensajeDTO mensaje = catalogoServicio.verificarListaVacia(catalogosVacios);
        assertEquals("La lista está vacía, no hay precios disponibles.", mensaje.getMensaje());
    }

    /**
     * TEST 2 - TESTSERVICIODISPOSITIVO
     */

    @Test
    void testServicioDisPositivo() {
        TipoPrenda tipoPrenda = TipoPrenda.CAMISA;
        TipoCatalogo tipoCatalogo = TipoCatalogo.LAVADO;

        MensajeDTO mensaje = catalogoServicio.servicioDisponible(tipoPrenda, tipoCatalogo);

        assertEquals("Servicio disponible", mensaje.getMensaje());
    }

    /**
     * TEST 2 - TESTSERVICIODISNEGATIVO
     */

    @Test
    void testServicioDisNegativo() {
        TipoPrenda tipoPrenda = TipoPrenda.CAMISA;
        TipoCatalogo tipoCatalogo = TipoCatalogo.LAVADO_PLANCHADO;

        MensajeDTO mensaje = catalogoServicio.servicioDisponible(tipoPrenda, tipoCatalogo);

        assertEquals("No disponible", mensaje.getMensaje());

    }

    /**
     * TEST 6 - TESTELIMINARPOSITIVO
     */

    @Test
    void testEliminarPositivo(){
        TipoPrenda tipoPrenda = TipoPrenda.CAMISA;
        TipoCatalogo tipoCatalogo = TipoCatalogo.LAVADO;

        MensajeDTO mensaje = catalogoServicio.eliminarServicio(1);

        assertEquals("No se ha podido eliminar, pago pendiente", mensaje.getMensaje());
    }

    /**
     * TEST 6 - TESTELIMINARNEGATIVO
     */

    @Test
    void testEliminarNegativo(){

        Integer idFake = 99;

        MensajeDTO mensaje = catalogoServicio.eliminarServicio(99);

        assertEquals("este no existe", mensaje.getMensaje());
    }

}
