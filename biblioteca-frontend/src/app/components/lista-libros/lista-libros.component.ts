import { Component, OnInit } from '@angular/core';
import { LibroService } from '../../services/libro.service';
import { Libro } from '../../models/libro.model';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lista-libros',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './lista-libros.component.html',
  styleUrl: './lista-libros.component.scss',
  providers: [LibroService]
})
export class ListaLibrosComponent implements OnInit {
  libros: Libro[] = [];

  constructor(private libroService: LibroService) {}

  ngOnInit(): void {
    this.cargarLibros();
  }

  cargarLibros(): void {
    this.libroService.getLibros().subscribe((data: Libro[]) => {
      this.libros = data;
      console.log("Libros cargados (array):", this.libros);
    });
  }

  eliminarLibro(id: number | undefined): void {
    if (id !== undefined) {
      this.libroService.deleteLibro(id).subscribe(() => {
        this.libros = this.libros.filter(libro => libro.id !== id);
      });
    }
  }
}
