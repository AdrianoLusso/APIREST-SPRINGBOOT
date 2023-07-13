package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="Inscripcion")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Inscripcion {

    public Inscripcion(
                Estado estado,
                LocalDate fechaInscripcion,
                Curso curso,
                Estudiante estudiante
    ) {
        this.estado = estado;
        this.fechaInscripcion = fechaInscripcion;
        this.curso = curso;
        this.estudiante = estudiante;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Column(name = "fechaInscripcion")
    private LocalDate fechaInscripcion;

    //Relaciones
    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "idCurso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "idEstudiante")
    private Estudiante estudiante;
}
