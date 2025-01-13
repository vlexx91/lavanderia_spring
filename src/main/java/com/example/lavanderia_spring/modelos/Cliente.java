package com.example.lavanderia_spring.modelos;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity

@Table(name = "cliente", schema = "lavanderia")
//name = es el nombre de la tabla en la base de datos
//schema = es el nombre del esquema en la base de datos
//catalog = es el nombre de lenguaje de base de datos usado

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Cliente {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremental

    @Column(name = "id") //mismo nombre que el campo de la base de datos
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name="dni")
    private String dni;

    @Column(name="fecha_nacimiento")
    private LocalDate fechaNacimiento;

    //@ManyToOne
    //N:1 -> Muchos clientes pueden tener una ciudad.

    //@OneToMany (targetEntity = Pedido.class, mappedBy = "cliente", Fetch = FetchType.LAZY)
    //mappedBy = "cliente" -> nombre del atributo en la clase Pedido
    //private Set<Ofertaempleo> ofertaEmpleo;

    //@ManyToMany
    //N:M -> Muchos clientes pueden tener muchas ciudades.
    //Tablas N:M se tratan como ManyToOne y OneToMany


}


