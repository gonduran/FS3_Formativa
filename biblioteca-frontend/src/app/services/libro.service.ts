import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Libro } from '../models/libro.model';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class LibroService {

  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  // GET: Obtener todos los libros
  getLibros(): Observable<Libro[]> {
    console.log(`GET request to ${this.apiUrl}`);
    return this.http.get<{ _embedded: { libroList: Libro[] } }>(`${this.apiUrl}`).pipe(
      map(response => {
        console.log('Response from getLibros:', response);
        return response._embedded.libroList;
      })
    );
  }

  // GET: Obtener un libro por ID
  getLibro(id: number): Observable<Libro> {
    console.log(`GET request to ${this.apiUrl}/${id}`);
    return this.http.get<Libro>(`${this.apiUrl}/${id}`).pipe(
      map(response => {
        console.log(`Response from getLibro (ID: ${id}):`, response);
        return response;
      })
    );
  }

  // POST: Agregar un nuevo libro
  addLibro(libro: Libro): Observable<Libro> {
    const { id, ...libroSinId } = libro;
    console.log(`POST request to ${this.apiUrl} with data:`, libroSinId);
    return this.http.post<Libro>(`${this.apiUrl}`, libroSinId).pipe(
      map(response => {
        console.log('Response from addLibro:', response);
        return response;
      })
    );
  }

  // PUT: Actualizar un libro existente
  updateLibro(id: number, libro: Libro): Observable<Libro> {
    console.log(`PUT request to ${this.apiUrl}/${id} with data:`, libro);
    return this.http.put<Libro>(`${this.apiUrl}/${id}`, libro).pipe(
      map(response => {
        console.log(`Response from updateLibro (ID: ${id}):`, response);
        return response;
      })
    );
  }

  // DELETE: Eliminar un libro por ID
  deleteLibro(id: number): Observable<void> {
    console.log(`DELETE request to ${this.apiUrl}/${id}`);
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      map(response => {
        console.log(`Response from deleteLibro (ID: ${id}):`, response);
        return response;
      })
    );
  }
}