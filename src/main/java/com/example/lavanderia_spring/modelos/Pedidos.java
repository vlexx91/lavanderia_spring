package com.example.lavanderia_spring.modelos;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos", schema = "lavanderia", catalog = "postgres")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Pedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha_entrega")
    private LocalDate fechaEntrega;

    @Column(name="total_precio")
    private Double totalPrecio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "id_pedidos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidosPrendasCatalogo> pedidosPrendasCatalogos;

}
