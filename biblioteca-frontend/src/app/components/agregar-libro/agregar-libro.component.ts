import { Component } from '@angular/core';
import { LibroService } from '../../services/libro.service';
import { Libro } from '../../models/libro.model';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-agregar-libro',
  standalone: true,
  templateUrl: './agregar-libro.component.html',
  styleUrls: ['./agregar-libro.component.scss'],
  providers: [LibroService],
  imports: [FormsModule]
})
export class AgregarLibroComponent {
  libro: Partial<Libro> = {
    titulo: '',
    autor: '',
    annoPublicacion: 0,
    genero: ''
  };

  constructor(private libroService: LibroService, private router: Router) {}

  agregarLibro(): void {
    this.libroService.addLibro(this.libro as Libro).subscribe(() => {
      this.router.navigate(['/libros']);
    });
  }

  cancelar(): void {
    this.router.navigate(['/libros']);
  }
}