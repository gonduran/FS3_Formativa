package com.example.biblioteca_ms_libros.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "Libros")  
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLibro")
    private Long id;

    @NotBlank(message = "No puede ingresar titulo vacio")
    @NotNull(message = "Titulo obligatorio")
    @Column(name = "titulo")
    private String titulo;

    @NotBlank(message = "No puede ingresar autor vacio")
    @NotNull(message = "Autor obligatorio")
    @Column(name = "autor")
    private String autor;

    @NotNull(message = "Año Publicacion obligatorio")
    @Column(name = "annoPublicacion")
    private int annoPublicacion;

    @NotBlank(message = "No puede ingresar genero vacio")
    @NotNull(message = "Genero obligatorio")
    @Column(name = "genero")
    private String genero;

    // Constructor vacío necesario para JPA
    public Libro() {}

    // Constructor con parámetros (opcional)
    public Libro(String titulo, String autor, int annoPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.annoPublicacion = annoPublicacion;
        this.genero = genero;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnnoPublicacion() {
        return annoPublicacion;
    }

    public void setAnnoPublicacion(int annoPublicacion) {
        this.annoPublicacion = annoPublicacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}