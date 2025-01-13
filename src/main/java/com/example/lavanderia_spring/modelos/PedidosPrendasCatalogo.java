package com.example.lavanderia_spring.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pedidos_prendas_catalogo", schema = "lavanderia")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class PedidosPrendasCatalogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="precio")
    private Float precio;

    @Column(name="cantidad")
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name="id_pedidos")
    private Pedidos id_pedidos;

    @ManyToOne
    @JoinColumn(name="id_prendas")
    private Prendas id_prendas;

    @ManyToOne
    @JoinColumn(name="id_catalogo")
    private Catalogo id_catalogo;

}
