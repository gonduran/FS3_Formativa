import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Libro } from '../models/libro.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  private apiUrl = 'http://backend:8091/libros';

  constructor(private http: HttpClient) { }

  // GET: Obtener todos los libros
  getLibros(): Observable<Libro[]> {
    return this.http.get<{ _embedded: { libroList: Libro[] } }>(this.apiUrl).pipe(
      map(response => response._embedded.libroList)  // Extrae el array de libros
    );
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
  updateLibro(id: number, libro: Libro): Observable<Libro> {
    return this.http.put<Libro>(`${this.apiUrl}/${id}`, libro);
  }

  // DELETE: Eliminar un libro por ID
  deleteLibro(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}