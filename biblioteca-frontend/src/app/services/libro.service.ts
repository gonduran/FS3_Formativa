import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Libro } from '../models/libro.model';

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  private apiUrl = 'http://localhost:8091/libros';

  constructor(private http: HttpClient) { }

  // GET: Obtener todos los libros
  getLibros(): Observable<Libro[]> {
    return this.http.get<Libro[]>(`${this.apiUrl}`);
  }

  // GET: Obtener un libro por ID
  getLibro(id: number): Observable<Libro> {
    return this.http.get<Libro>(`${this.apiUrl}/${id}`);
  }

  // POST: Agregar un nuevo libro
  addLibro(libro: Libro): Observable<Libro> {
    const { id, ...libroSinId } = libro; // Descartar el 'id' en caso de estar presente
    return this.http.post<Libro>(`${this.apiUrl}`, libroSinId);
  }

  // PUT: Actualizar un libro existente
  updateLibro(libro: Libro): Observable<Libro> {
    if (!libro.id) {
      throw new Error("El libro debe tener un ID para ser actualizado.");
    }
    return this.http.put<Libro>(`${this.apiUrl}/${libro.id}`, libro);
  }

  // DELETE: Eliminar un libro por ID
  deleteLibro(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}