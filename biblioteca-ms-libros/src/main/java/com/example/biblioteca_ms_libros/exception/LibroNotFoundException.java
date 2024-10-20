package com.example.biblioteca_ms_libros.exception;

public class LibroNotFoundException extends RuntimeException {
    public LibroNotFoundException(String message) {
        super(message);
    }
}
