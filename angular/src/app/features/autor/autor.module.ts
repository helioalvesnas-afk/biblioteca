import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AutorRoutingModule } from './autor-routing.module';
import { AutorFormComponent } from './autor-form/autor-form.component';
import { AutorListComponent } from './autor-list/autor-list.component';
import { AutorListItemComponent } from './autor-list-item/autor-list-item.component';
import { FormsModule } from '@angular/forms';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        AutorRoutingModule,
        AutorFormComponent, AutorListComponent, AutorListItemComponent
    ],
    exports: [AutorFormComponent, AutorListComponent, AutorListItemComponent],
})
export class AutorModule {}
