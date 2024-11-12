package com.example.biblio_ms_libros.service;

import com.example.biblio_ms_libros.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> getAllLibros();
    Optional<Libro> getLibroById(Long id);
    Libro createLibro(Libro libro);
    Libro updateLibro(Long id,Libro libro);
    void deleteLibro(Long id);
}