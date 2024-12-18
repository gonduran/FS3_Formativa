package com.example.biblio_ms_libros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblio_ms_libros.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
    Libro findByTitulo(String titulo);
}
