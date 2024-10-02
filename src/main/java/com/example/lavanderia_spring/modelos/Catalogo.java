package com.example.lavanderia_spring.modelos;

import jakarta.persistence.*;
import lombok.*;

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
    private Float precioServPrenda;

    @Column(name="tipo_prenda")
    private Integer tipoPrenda;

    @Column(name="tipo_servicio")
    private Integer tipoServicio;

}
