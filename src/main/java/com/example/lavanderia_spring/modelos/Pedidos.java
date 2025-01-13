package com.example.lavanderia_spring.modelos;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos", schema = "lavanderia")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL) //unica forma de que no me muestre el null de pedidosPrendasCatalogo

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
