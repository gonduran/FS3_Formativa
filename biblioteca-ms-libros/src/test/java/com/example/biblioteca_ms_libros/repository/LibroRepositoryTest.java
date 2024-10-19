package com.example.biblioteca_ms_libros.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import com.example.biblioteca_ms_libros.model.Libro;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LibroRepositoryTest {
    @Autowired
    private LibroRepository libroRepository;

    @Test
    public void guardarLibroTest() {
        Libro libro = new Libro();
        libro.setTitulo("Matar a un ruiseñor");
        libro.setAutor("Harper Lee");
        libro.setAnnoPublicacion(1960);
        libro.setGenero("Ficción / Drama");

        Libro resultado = libroRepository.save(libro);

        assertNotNull(resultado.getId());
        assertEquals("Matar a un ruiseñor", resultado.getTitulo());
        assertEquals("Harper Lee", resultado.getAutor());
    }

    @Test
    public void obtenerLibroPorIdTest() {
        // Insertar un libro manualmente
        Libro libro = new Libro();
        libro.setTitulo("Matar a un ruiseñor");
        libro.setAutor("Harper Lee");
        libro.setAnnoPublicacion(1960);
        libro.setGenero("Ficción / Drama");

        // Guardar el libro en la base de datos
        Libro guardado = libroRepository.save(libro);

        // Buscar el libro por ID
        Optional<Libro> resultado = libroRepository.findById(guardado.getId());

        // Verificar que el Optional contiene un valor
        assertTrue(resultado.isPresent(), "El libro no fue encontrado");

        // Obtener el valor de Optional
        Libro libroEncontrado = resultado.get();

        // Verificar los datos
        assertNotNull(libroEncontrado);
        assertEquals(libro.getId(), libroEncontrado.getId());
        assertEquals(libro.getTitulo(), libroEncontrado.getTitulo());
    }
}
