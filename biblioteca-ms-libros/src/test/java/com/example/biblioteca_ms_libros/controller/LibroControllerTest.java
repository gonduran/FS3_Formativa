package com.example.biblioteca_ms_libros.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.example.biblioteca_ms_libros.model.Libro;
import com.example.biblioteca_ms_libros.service.LibroService;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroServiceMock;

    @Test
    public void getAllLibrosTest() throws Exception {
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

        when(libroServiceMock.getAllLibros()).thenReturn(libros);

        mockMvc.perform(MockMvcRequestBuilders.get("/libros"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getLibroByIdTest() throws Exception {
        Libro libro = new Libro();
        libro.setTitulo("Matar a un ruiseñor");
        libro.setAutor("Harper Lee");
        libro.setAnnoPublicacion(1960);
        libro.setGenero("Ficción / Drama");
        libro.setId(1L);

        when(libroServiceMock.getLibroById(1L)).thenReturn(Optional.of(libro));

        Optional<Libro> resultado = libroServiceMock.getLibroById(1L);

        assertTrue(resultado.isPresent());
        assertEquals("Matar a un ruiseñor", resultado.get().getTitulo());

        mockMvc.perform(MockMvcRequestBuilders.get("/libros/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
