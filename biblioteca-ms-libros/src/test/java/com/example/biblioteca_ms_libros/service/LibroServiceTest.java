package com.example.biblioteca_ms_libros.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.biblioteca_ms_libros.model.Libro;
import com.example.biblioteca_ms_libros.repository.LibroRepository;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTest {
    @InjectMocks
    private LibroServiceImpl libroService;

    @Mock
    private LibroRepository libroRepositoryMock;

    @Test
    public void getAllLibrosTest() {
        Libro libro1 = new Libro();
        libro1.setTitulo("Matar a un ruiseñor");
        libro1.setAutor("Harper Lee");
        libro1.setAnnoPublicacion(1960);
        libro1.setGenero("Ficción / Drama");
        libro1.setId(1L);

        Libro libro2 = new Libro();
        libro2.setTitulo("Crimen y castigo");
        libro2.setAutor("Fiódor Dostoyevski");
        libro2.setAnnoPublicacion(1866);
        libro2.setGenero("Novela Filosófica / Psicología");
        libro2.setId(2L);

        List<Libro> libros = Arrays.asList(libro1, libro2);

        when(libroRepositoryMock.findAll()).thenReturn(libros);

        List<Libro> resultado = libroService.getAllLibros();

        assertEquals(2, resultado.size());
        assertEquals("Matar a un ruiseñor", resultado.get(0).getTitulo());
        assertEquals("Crimen y castigo", resultado.get(1).getTitulo());
    }

    @Test
    public void getLibroByIdTest() {
        Libro libro = new Libro();
        libro.setTitulo("Matar a un ruiseñor");
        libro.setAutor("Harper Lee");
        libro.setAnnoPublicacion(1960);
        libro.setGenero("Ficción / Drama");
        libro.setId(1L);

        when(libroRepositoryMock.findById(1L)).thenReturn(Optional.of(libro));

        Optional<Libro> resultado = libroService.getLibroById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Matar a un ruiseñor", resultado.get().getTitulo());
    }

    @Test
    public void createLibroTest() {

        Libro libro = new Libro();
        libro.setTitulo("Matar a un ruiseñor");
        libro.setAutor("Harper Lee");
        libro.setAnnoPublicacion(1960);
        libro.setGenero("Ficción / Drama");
        libro.setId(1L);

        when(libroRepositoryMock.save(any())).thenReturn(libro);

        Libro resultado = libroService.createLibro(libro);

        assertEquals("Matar a un ruiseñor", resultado.getTitulo());
    }

    @Test
    public void updateLibroTest_Exists() {
        Libro libro = new Libro();
        libro.setTitulo("Matar a un ruiseñor");
        libro.setAutor("Harper Lee");
        libro.setAnnoPublicacion(1960);
        libro.setGenero("Ficción / Drama");
        libro.setId(1L);

        when(libroRepositoryMock.existsById(1L)).thenReturn(true);
        when(libroRepositoryMock.save(libro)).thenReturn(libro);

        Libro resultado = libroService.updateLibro(1L, libro);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Matar a un ruiseñor", resultado.getTitulo());
    }

    @Test
    public void updateLibroTest_NotExists() {
        Libro libro = new Libro();
        libro.setTitulo("Crimen y castigo");
        libro.setAutor("Fiódor Dostoyevski");
        libro.setAnnoPublicacion(1866);
        libro.setGenero("Novela Filosófica / Psicología");
        libro.setId(2L);

        when(libroRepositoryMock.existsById(2L)).thenReturn(false);

        Libro resultado = libroService.updateLibro(2L, libro);

        assertNull(resultado);
    }
}
