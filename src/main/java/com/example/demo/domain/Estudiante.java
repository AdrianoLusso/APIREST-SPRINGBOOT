package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Estudiante")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Estudiante {

    public Estudiante(
            int dni,
            String email,
            LocalDate fechaDeNacimiento,
            String apellido,
            String nombre)
    {
        this.dni = dni;
        this.email = email;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.apellido = apellido;
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dni")
    private int dni;

    @Column(name = "email")
    private String email;

    @Column(name = "fechaDeNacimiento")
    private LocalDate fechaDeNacimiento;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    public int calculateEdad()
    {
        return Period.between(this.fechaDeNacimiento,LocalDate.now()).getYears();

    }
}
