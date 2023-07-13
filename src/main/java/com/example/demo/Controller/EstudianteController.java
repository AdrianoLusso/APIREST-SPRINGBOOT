package com.example.demo.Controller;

import com.example.demo.Service.EstudianteService;
import com.example.demo.dto.EstudianteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @PostMapping
    public EstudianteDTO createEstudiante(@RequestBody EstudianteDTO estudianteDTO)
    {
        return estudianteService.createEstudiante(estudianteDTO);
    }

    @GetMapping
    public List<EstudianteDTO> findAll()
    {
        return estudianteService.findAll();
    }

    @GetMapping("/{id}")
    public EstudianteDTO find(@PathVariable Long id)
    {
        return estudianteService.find(id);
    }

    @PutMapping("/{id}")
    public EstudianteDTO update(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO)
    {
        return estudianteService.update(id,estudianteDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        estudianteService.delete(id);
    }
}
