package com.example.biblioteca_ms_libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblioteca_ms_libros.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    Libro findByLibro(String libro);
}
