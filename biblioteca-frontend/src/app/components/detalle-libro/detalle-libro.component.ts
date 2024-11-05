import { Component, OnInit } from '@angular/core';
import { LibroService } from '../../services/libro.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-detalle-libro',
  standalone: true,
  imports: [],
  templateUrl: './detalle-libro.component.html',
  styleUrl: './detalle-libro.component.scss'
})
export class DetalleLibroComponent implements OnInit {
  libro: any;

  constructor(private libroService: LibroService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.libroService.getLibro(id).subscribe(data => {
      this.libro = data;
    });
  }
}