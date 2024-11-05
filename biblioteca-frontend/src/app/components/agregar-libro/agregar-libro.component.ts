import { Component } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { LibroService, Libro } from '../../services/libro.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-libro',
  standalone: true,
  imports: [],
  templateUrl: './agregar-libro.component.html',
  styleUrl: './agregar-libro.component.scss'
})
export class AgregarLibroComponent {
  libro: Omit<Libro, 'id'> = {
    titulo: '',
    autor: '',
    annoPublicacion: 0,
    genero: ''
  };

  constructor(private libroService: LibroService, private router: Router) {}

  agregarLibro(): void {
    this.libroService.addLibro(this.libro).subscribe(() => {
      this.router.navigate(['/libros']);
    });
  }
}