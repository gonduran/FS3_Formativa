import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LibroService } from '../../services/libro.service';
import { Libro } from '../../models/libro.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-detalle-libro',
  standalone: true,
  templateUrl: './detalle-libro.component.html',
  styleUrls: ['./detalle-libro.component.scss'],
  providers: [LibroService],
  imports: [CommonModule] // Asegúrate de incluir CommonModule aquí
})
export class DetalleLibroComponent implements OnInit {
  libro: Libro | null = null;

  constructor(
    private libroService: LibroService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.libroService.getLibro(id).subscribe(
        (data: Libro) => (this.libro = data),
        () => (this.libro = null) // Manejo de errores
      );
    }
  }

  volver(): void {
    this.router.navigate(['/libros']);
  }
}