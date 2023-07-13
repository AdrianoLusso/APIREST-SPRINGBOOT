package com.example.demo.repository;

import com.example.demo.domain.Estado;
import com.example.demo.domain.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion,Long> {

    List<Inscripcion> findByEstadoIn(List<Estado> estado);
    List<Inscripcion> findByEstado(Estado estado);
    @Query(value = "SELECT i FROM Inscripcion i WHERE i.estudiante = :idEstudiante")
    List<Inscripcion> findByIdEstudiante(@Param("idEstudiante") Long idEstudiante);
    @Query(value = "SELECT * FROM Inscripcion WHERE estado = :estado ",nativeQuery = true)
    List<Inscripcion> findByEstadoNative(@Param("estado") String estado);
}
