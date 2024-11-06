import { Component, OnInit } from '@angular/core';
import { LibroService } from '../../services/libro.service';
import { Libro } from '../../models/libro.model';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-editar-libro',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './editar-libro.component.html',
  styleUrl: './editar-libro.component.scss',
  providers: [LibroService]
})
export class EditarLibroComponent implements OnInit {
  libro: Libro = {
    id: 0,
    titulo: '',
    autor: '',
    annoPublicacion: 0,
    genero: ''
  };

  constructor(
    private libroService: LibroService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (id) {
      this.libroService.getLibro(id).subscribe((data: any) => {
        this.libro = {
          id: data.id,
          titulo: data.titulo,
          autor: data.autor,
          annoPublicacion: data.annoPublicacion,
          genero: data.genero
        };
        console.log("Libro:", this.libro);
      });
    } else {
      console.error('ID no encontrado en la ruta');
    }
  }

  actualizarLibro(): void {
    if (this.libro.id) {
      this.libroService.updateLibro(this.libro.id, this.libro).subscribe(() => {
        this.router.navigate(['/libros']);
      });
    }
  }

  cancelarEdicion(): void {
    this.router.navigate(['/libros']);
  }
}