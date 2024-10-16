package com.example.lavanderia_spring;


import com.example.lavanderia_spring.modelos.Catalogo;
import com.example.lavanderia_spring.modelos.Cliente;
import com.example.lavanderia_spring.modelos.Pedidos;
import com.example.lavanderia_spring.servicios.CatalogoServicio;
import com.example.lavanderia_spring.servicios.ClienteServicio;
import com.example.lavanderia_spring.servicios.PedidosServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class CatalogoTest {

    @Autowired
    private CatalogoServicio catalogoServicio;

    @Autowired
    private PedidosServicio pedidosServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Test
    void testGetAllCatalogo(){
        List<Catalogo> catalogos = catalogoServicio.getAll();
        for (Catalogo c : catalogos){
            System.out.println(c);
        }
    }

//    @Test
//    void testCrearCatalogo() {
//        Catalogo catalogo = new Catalogo();
//        catalogo.setPrecioServPrenda(50.0);
//        catalogo.setTipoPrenda(2);
//        catalogo.setTipoServicio(1);
//        Catalogo catalogoGuardado = catalogoServicio.crearCatalogo(catalogo);
//        System.out.println(catalogoGuardado);
//    }


}
