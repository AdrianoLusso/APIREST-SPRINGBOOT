package com.example.demo.Service;

import com.example.demo.domain.Estudiante;
import com.example.demo.dto.EstudianteDTO;
import com.example.demo.repository.EstudianteRepository;
import com.example.demo.repository.InscripcionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Transactional
    public EstudianteDTO createEstudiante(EstudianteDTO estudianteDTO)
    {
        Estudiante estudiante = new Estudiante(
                estudianteDTO.getDni(),
                estudianteDTO.getEmail(),
                estudianteDTO.getFechaDeNacimiento(),
                estudianteDTO.getApellido(),
                estudianteDTO.getNombre());

        estudianteRepository.save(estudiante);

        return estudianteDTO;
    }

    public List<EstudianteDTO> findAll()
    {
        return estudianteRepository.findAll().
                stream().map(c -> new EstudianteDTO(
                        c.getDni(),
                        c.getEmail(),
                        c.getFechaDeNacimiento(),
                        c.getNombre(),
                        c.getApellido()
                )).collect(Collectors.toList());
    }

    @Transactional
    public EstudianteDTO update(Long id, EstudianteDTO estudianteDTO)
    {
        Estudiante estudiante = new Estudiante(
                id, estudianteDTO.getDni(),
                estudianteDTO.getEmail(),
                estudianteDTO.getFechaDeNacimiento(),
                estudianteDTO.getApellido(),
                estudianteDTO.getNombre()
        );

        estudianteRepository.save(estudiante);
        return estudianteDTO;
    }

    public EstudianteDTO find(Long id)
    {
        Optional<Estudiante> estudianteWrapper = estudianteRepository.findById(id);

        if(estudianteWrapper.isEmpty())
        {
            throw new RuntimeException("Id invalido");
        }

        Estudiante estudiante = estudianteWrapper.get();

        return new EstudianteDTO(
                estudiante.getDni(),
                estudiante.getEmail(),
                estudiante.getFechaDeNacimiento(),
                estudiante.getApellido(),
                estudiante.getNombre());
    }

    @Transactional
    public void delete(Long id)
    {
        if(!inscripcionRepository.findByIdEstudiante(id).isEmpty())
        {
            throw new RuntimeException("ERROR: No puede eliminarse porque existe una inscripcion con el estudiante.");
        }
        estudianteRepository.deleteById(id);
    }
}
