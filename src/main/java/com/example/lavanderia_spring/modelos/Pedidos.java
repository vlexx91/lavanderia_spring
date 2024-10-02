package com.example.lavanderia_spring.modelos;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

}
