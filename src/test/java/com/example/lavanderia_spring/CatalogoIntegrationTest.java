package com.example.lavanderia_spring;


import com.example.lavanderia_spring.dto.CatalogoDTO;
import com.example.lavanderia_spring.dto.MensajeDTO;
import com.example.lavanderia_spring.enumerados.TipoCatalogo;
import com.example.lavanderia_spring.enumerados.TipoPrenda;
import com.example.lavanderia_spring.modelos.Catalogo;
import com.example.lavanderia_spring.repositorios.CatalogoRepositorio;
import com.example.lavanderia_spring.servicios.CatalogoServicio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CatalogoIntegrationTest {

    @InjectMocks
    private CatalogoServicio catalogoServicio;

    @Mock
    private CatalogoRepositorio catalogoRepositorio;

    //ejemplo test prueba luis
    @Test
    public void testFindByIdIntegracion() throws Exception{

        //GIVEN
        Mockito.when(catalogoRepositorio.findById(Mockito.any())).thenReturn(Optional.ofNullable(null));

        //WHEN && THEN

        assertThrows(Exception.class, ()-> catalogoServicio.getById(3));
        Mockito.verify(catalogoRepositorio, Mockito.times(1)).findById(3);

    }

    //TEST 1 - LISTA
    @Test
    public void testFindAllIntegracion(){

        //GIVEN
        List<Catalogo> catalogoDTOList = new ArrayList<>();
        Catalogo c1 = new Catalogo();
        c1.setTipoCatalogo(TipoCatalogo.LAVADO_PLANCHADO);
        c1.setTipoPrenda(TipoPrenda.BLUSA);
        c1.setPrecioServPrenda(50.00);

        catalogoDTOList.add(c1);

        Catalogo c2 = new Catalogo();
        c2.setTipoCatalogo(TipoCatalogo.LAVADO);
        c2.setTipoPrenda(TipoPrenda.CAMISA);
        c2.setPrecioServPrenda(30.00);

        catalogoDTOList.add(c2);

        Mockito.when(catalogoRepositorio.findAll()).thenReturn(catalogoDTOList);

        //WHEN
        List<CatalogoDTO> catalogo = catalogoServicio.getAllDTO();

        //THEN
        assertEquals(2, catalogo.size());
        Mockito.verify(catalogoRepositorio, Mockito.times(1)).findAll();

    }

    //TEST 2 - TESTSERVICIO

    @Test
    public void testServicioDisIntegracion() {

        // GIVEN
        TipoPrenda tipoPrenda1 = TipoPrenda.CAMISA;
        TipoCatalogo tipoCatalogo1 = TipoCatalogo.LAVADO;
        Catalogo catalogo1 = new Catalogo();
        catalogo1.setTipoPrenda(tipoPrenda1);
        catalogo1.setTipoCatalogo(tipoCatalogo1);

        List<Catalogo> catalogos = new ArrayList<>();
        catalogos.add(catalogo1);

        Mockito.when(catalogoRepositorio.findAll()).thenReturn(catalogos);

        // WHEN
        MensajeDTO mensaje1 = catalogoServicio.servicioDisponible(tipoPrenda1, tipoCatalogo1);

        // THEN
        assertEquals("Servicio disponible", mensaje1.getMensaje());

        // GIVEN
        TipoPrenda tipoPrenda2 = TipoPrenda.BLUSA;
        TipoCatalogo tipoCatalogo2 = TipoCatalogo.LAVADO;

        Mockito.when(catalogoRepositorio.findAll()).thenReturn(new ArrayList<>());

        // WHEN
        MensajeDTO mensaje2 = catalogoServicio.servicioDisponible(tipoPrenda2, tipoCatalogo2);

        // THEN
        assertEquals("No disponible", mensaje2.getMensaje());
    }
}
