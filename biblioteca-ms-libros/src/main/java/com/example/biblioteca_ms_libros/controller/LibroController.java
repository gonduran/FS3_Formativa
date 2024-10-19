package com.example.biblioteca_ms_libros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca_ms_libros.model.Libro;
import com.example.biblioteca_ms_libros.exception.LibroBadRequestException;
import com.example.biblioteca_ms_libros.exception.LibroNotFoundException;
import com.example.biblioteca_ms_libros.service.LibroService;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/libros")
public class LibroController {
    private static final Logger log = LoggerFactory.getLogger(LibroController.class);

    @Autowired
    private LibroService libroService;

    //devuelve la informacion de todos los libros
    @GetMapping
    public CollectionModel<EntityModel<Libro>> getAllLibros() {
        List<Libro> libros = libroService.getAllLibros();
        log.info("GET /libros");
        log.info("Devuelve la informacion de todos los libros");
        List<EntityModel<Libro>> librosResources = libros.stream()
            .map( libro -> EntityModel.of(libro,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(libro.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteLibro(libro.getId())).withRel("delete")
            ))
            .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros());
        CollectionModel<EntityModel<Libro>> resources = CollectionModel.of(librosResources, linkTo.withRel("libros"));

        return resources;
    }

    //devuelve la informacion de un libro especifico
    @GetMapping("/{idLibro}")
    public EntityModel<Libro> getLibroById(@PathVariable("idLibro") Long idLibro) {
        Optional<Libro> libro = libroService.getLibroById(idLibro);
        log.info("GET /libros/{idLibro}");
        log.info("Se ejecuta getLibroById: {}", idLibro);
        if (libro.isPresent()) {
            log.info("Se encontró el libro con ID {}", idLibro);
            return EntityModel.of(libro.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(idLibro)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).deleteLibro(idLibro)).withRel("delete"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros()).withRel("all-libros"));
        } else {
            log.error("No se encontró el libro con ID {}", idLibro);
            throw new LibroBadRequestException("No se encontró el libro con ID: " + idLibro);
        }
    }

    @PostMapping
    public EntityModel<Libro> createLibro(@Validated @RequestBody Libro libro) {
        log.info("POST /libros");
        log.info("Se ejecuta createLibro");
        Libro createLibro = libroService.createLibro(libro);
        if (createLibro == null) {
            log.error("Error al crear el libro {}", libro);
            throw new LibroBadRequestException("Error al crear el libro");
        }
        return EntityModel.of(createLibro,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(createLibro.getId())).withSelfRel(),
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros()).withRel("all-libross"));
    }
        
    @PutMapping("/{idLibro}")
    public EntityModel<Libro> updateLibro(@PathVariable("idLibro") Long idLibro, @RequestBody Libro libro) {
        log.info("PUT /libros/{idLibro}");
        log.info("Se ejecuta updateLibro: {}", idLibro);
        Optional<Libro> libroFind = libroService.getLibroById(idLibro);
        if (libroFind.isEmpty()) {
            log.error("No se encontró el libro con ID {}", idLibro);
            throw new LibroNotFoundException("No se encontró el libro con ID: " + idLibro);
        }
        log.info("Se encontró y actualizo el libro con ID {}", idLibro);
        Libro updateLibro = libroService.updateLibro(idLibro, libro);
        return EntityModel.of(updateLibro,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getLibroById(idLibro)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllLibros()).withRel("all-libros"));
    }

    @DeleteMapping("/{idLibro}")
    public ResponseEntity<Object> deleteLibro(@PathVariable("idLibro") Long idLibro){
        log.info("DELETE /libros/{idLibro}");
        Optional<Libro> libroFind = libroService.getLibroById(idLibro);
        if (libroFind.isEmpty()) {
            log.error("No se encontró el libro con ID {}", idLibro);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("No se encontró el libro con ID " + idLibro));
        }
        log.info("Se encontró y elimino el libro con ID {}", idLibro);
        libroService.deleteLibro(idLibro);
        return ResponseEntity.status(HttpStatus.OK).body(new ErrorResponse("Se encontró y elimino el libro con ID " + idLibro));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error en el servidor: " + e.getMessage());
    }

    @ExceptionHandler(LibroNotFoundException.class)
    public ResponseEntity<String> handlePacienteNotFoundException(LibroNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(e.getMessage());
    }

    @ExceptionHandler(LibroBadRequestException.class)
    public ResponseEntity<String> handlePacienteBadRequestException(LibroBadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }   
}
