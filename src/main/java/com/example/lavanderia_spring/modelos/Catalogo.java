package com.example.lavanderia_spring.modelos;

import com.example.lavanderia_spring.enumerados.TipoCatalogo;
import com.example.lavanderia_spring.enumerados.TipoPrenda;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "catalogo", schema = "lavanderia", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Catalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="precio_serv_prenda")
    private Double precioServPrenda;

    @Column(name="tipo_prenda")
    @Enumerated(EnumType.ORDINAL)
    private TipoPrenda tipoPrenda;

    @Column(name="tipo_servicio")
    @Enumerated(EnumType.ORDINAL)
    private TipoCatalogo tipoCatalogo;

    @OneToMany(mappedBy = "id_catalogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosPrendasCatalogo> pedidosPrendasCatalogos;

}
