export class Libro {
    id?: number;  // Hacemos que 'id' sea opcional
    titulo: string;
    autor: string;
    annoPublicacion: number;
    genero: string;
  
    constructor(titulo: string, autor: string, annoPublicacion: number, genero: string, id?: number) {
      this.id = id;
      this.titulo = titulo;
      this.autor = autor;
      this.annoPublicacion = annoPublicacion;
      this.genero = genero;
    }
  }