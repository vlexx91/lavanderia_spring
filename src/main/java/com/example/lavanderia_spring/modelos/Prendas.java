package com.example.lavanderia_spring.modelos;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prendas", schema = "lavanderia")

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Prendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name="descripcion")
    private String descripcion;
}
