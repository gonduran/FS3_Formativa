import { Component, OnInit } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';
import { LibroService } from '../../services/libro.service';
import { Libro } from '../../models/libro.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-libro',
  standalone: true,
  imports: [],
  templateUrl: './editar-libro.component.html',
  styleUrl: './editar-libro.component.scss'
})
export class EditarLibroComponent implements OnInit {
  libro: Libro = {
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
    this.libroService.getLibro(id).subscribe(data => {
      this.libro = data;
    });
  }

  actualizarLibro(): void {
    this.libroService.updateLibro(this.libro).subscribe(() => {
      this.router.navigate(['/libros']);
    });
  }
}