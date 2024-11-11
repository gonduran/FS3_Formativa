import { Routes } from '@angular/router';
import { ListaLibrosComponent } from './components/lista-libros/lista-libros.component';
import { DetalleLibroComponent } from './components/detalle-libro/detalle-libro.component';
import { AgregarLibroComponent } from './components/agregar-libro/agregar-libro.component';
import { EditarLibroComponent } from './components/editar-libro/editar-libro.component';

export const routes: Routes = [
    { path: 'libros', component: ListaLibrosComponent },
    { path: 'libros/:id', component: DetalleLibroComponent },
    { path: 'agregar-libro', component: AgregarLibroComponent },
    { path: 'editar-libro/:id', component: EditarLibroComponent },
    { path: '', redirectTo: '/libros', pathMatch: 'full' }
];
