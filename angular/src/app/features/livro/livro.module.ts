import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LivroRoutingModule } from './livro-routing.module';
import { LivroFormComponent } from './livro-form/livro-form.component';
import { LivroListComponent } from './livro-list/livro-list.component';
import { LivroListItemComponent } from './livro-list-item/livro-list-item.component';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        LivroRoutingModule,
        LivroFormComponent, LivroListComponent, LivroListItemComponent
    ],
    exports: [LivroFormComponent, LivroListComponent, LivroListItemComponent],
})
export class LivroModule {}
