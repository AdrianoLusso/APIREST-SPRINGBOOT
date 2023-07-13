package com.example.demo.dto;

import com.example.demo.domain.Estado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InscripcionDTO {

    private Estado estado;
    private LocalDate fechaInscripcion;
    private Long idCurso;
    private Long idEstudiante;
}
