package com.example.lavanderia_spring.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagos", schema = "lavanderia")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="pagado")
    private Boolean pagado;

    @Column(name = "cantidaddebida")
    private Double cantidadDebida;

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name="id_pedidos")
    private Pedidos pedidos;

}
