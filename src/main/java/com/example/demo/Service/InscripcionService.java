package com.example.demo.Service;

import com.example.demo.domain.Curso;
import com.example.demo.domain.Estado;
import com.example.demo.domain.Estudiante;
import com.example.demo.domain.Inscripcion;
import com.example.demo.dto.InscripcionDTO;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class InscripcionService {

    @Autowired
    InscripcionRepository inscripcionRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    EstudianteRepository estudianteRepository;

    @Transactional
    public InscripcionDTO createInscripcion(@NotNull @Positive Long idCurso, @NotNull @Positive Long idEstudiante)
    {
        //Optional sirve como wrapper para tener funciones que traten el hecho de que, tal vez, no exista el elemento
        //En este caso, no uso Optional, pero seria Optional<Tipo>
        Curso curso = cursoRepository.findById(idCurso).orElseThrow(
                () -> new RuntimeException("ERROR:No existe un curso con el id ingresado.")
        );
        Estudiante estudiante = estudianteRepository.findById(idEstudiante).orElseThrow(
                () -> new RuntimeException("ERROR:No existe un estudiante con el id ingresado.")
        );
        if(estudiante.calculateEdad()<18){throw new RuntimeException("ERROR:El estudiante es menor de edad.");}

        Inscripcion inscripcion = new Inscripcion(Estado.PENDIENTE, LocalDate.now(),curso,estudiante);
        inscripcionRepository.save(inscripcion);

        return new InscripcionDTO(
                inscripcion.getEstado(),
                inscripcion.getFechaInscripcion(),
                idCurso,
                idEstudiante
        );
    }
}
