package com.example.demo.Controller;

import com.example.demo.Service.CursoService;
import com.example.demo.dto.CursoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public CursoDTO createCurso(@RequestBody CursoDTO cursoDTO)
    {
        return cursoService.createCurso(cursoDTO);
    }
}
