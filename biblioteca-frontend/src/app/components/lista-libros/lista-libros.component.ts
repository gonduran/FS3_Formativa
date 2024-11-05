import { Component, OnInit } from '@angular/core';
import { LibroService } from '../../services/libro.service';

@Component({
  selector: 'app-lista-libros',
  standalone: true,
  imports: [],
  templateUrl: './lista-libros.component.html',
  styleUrl: './lista-libros.component.scss'
})
export class ListaLibrosComponent implements OnInit {
  libros: any[] = [];

  constructor(private libroService: LibroService) {}

  ngOnInit(): void {
    this.libroService.getLibros().subscribe(data => {
      this.libros = data;
    });
  }

  eliminarLibro(id: number): void {
    this.libroService.deleteLibro(id).subscribe(() => {
      this.libros = this.libros.filter(libro => libro.id !== id);
    });
  }
}
