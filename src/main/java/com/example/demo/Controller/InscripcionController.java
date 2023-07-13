package com.example.demo.Controller;

import com.example.demo.Service.InscripcionService;
import com.example.demo.dto.InscripcionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inscripcion")
public class InscripcionController {

    @Autowired
    InscripcionService inscripcionService;

    @PostMapping("/{idCurso}/{idEstudiante}")
    public InscripcionDTO createInscripcion(@PathVariable Long idCurso, @PathVariable Long idEstudiante)
    {
        return inscripcionService.createInscripcion(idCurso,idEstudiante);
    }
}
