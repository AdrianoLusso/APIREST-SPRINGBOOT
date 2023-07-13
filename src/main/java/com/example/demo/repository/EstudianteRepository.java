package com.example.demo.repository;

import com.example.demo.domain.Estudiante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Long> {

    List<Estudiante> findAll();

    List<Estudiante> findByApellidoAndDniGreaterThan(String apellido,int dni);

    Page<Estudiante> findAllByOrderByDniAsc(Pageable pageable);
}
