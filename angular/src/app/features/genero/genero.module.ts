import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GeneroRoutingModule } from './genero-routing.module';
import { GeneroListComponent } from './genero-list/genero-list.component';
import { GeneroListItemComponent } from './genero-list-item/genero-list-item.component';
import { GeneroFormComponent } from './genero-form/genero-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        GeneroRoutingModule,
        GeneroFormComponent, 
        GeneroListComponent, 
        GeneroListItemComponent
    ],
    exports: [GeneroFormComponent, GeneroListComponent, GeneroListItemComponent],
})
export class GeneroModule {}
