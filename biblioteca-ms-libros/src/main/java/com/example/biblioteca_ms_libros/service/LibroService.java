package com.example.biblioteca_ms_libros.service;

import com.example.biblioteca_ms_libros.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> getAllLibros();
    Optional<Libro> getLibroById(Long id);
    Libro createLibro(Libro usuario);
    Libro updateLibro(Long id,Libro usuario);
    void deleteLibro(Long id);
}