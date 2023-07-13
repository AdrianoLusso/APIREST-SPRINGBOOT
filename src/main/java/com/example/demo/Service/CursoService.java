package com.example.demo.Service;

import com.example.demo.domain.Curso;
import com.example.demo.dto.CursoDTO;
import com.example.demo.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public CursoDTO createCurso(CursoDTO cursoDTO)
    {
        Curso curso = new Curso(
                cursoDTO.getDescripcion(),
            cursoDTO.getFechaDeInicio(),
            cursoDTO.getFechaDeFin(),
            cursoDTO.getNombre()
        );

        cursoRepository.save(curso);

        return cursoDTO;
    }
}
