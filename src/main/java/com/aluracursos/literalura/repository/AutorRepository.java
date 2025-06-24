package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(Integer anioFin, Integer anioInicio);
}
