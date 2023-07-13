package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Curso {


    public Curso(
            String descripcion,
            LocalDate fechaDeInicio,
            LocalDate fechaDeFin,
            String nombre
    )
    {
        this.descripcion = descripcion;
        this.fechaDeInicio = fechaDeInicio;
        this.fechaDeFin = fechaDeFin;
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fechaDeInicio")
    private LocalDate fechaDeInicio;

    @Column(name = "fechaDeFin")
    private LocalDate fechaDeFin;

    @Column(name="nombre")
    private String nombre;

}
