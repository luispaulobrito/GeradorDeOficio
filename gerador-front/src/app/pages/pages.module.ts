import { NgModule } from '@angular/core';
import { CommonModule, KeyValuePipe } from '@angular/common';

import { PagesRoutingModule } from './pages-routing.module';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from '../theme/components/menu/menu.component';
import { FooterComponent } from '../theme/components/footer/footer.component';
import { NewLetterComponent } from './new-letter/new-letter.component';
import { InitialContentComponent } from './initial-content/initial-content.component';
import { ListLetterComponent } from './list-letter/list-letter.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { CalendarModule } from 'primeng/calendar';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';

@NgModule({
  declarations: [
    HomeComponent,
    MenuComponent,
    FooterComponent,
    NewLetterComponent,
    InitialContentComponent,
    ListLetterComponent
  ],
  imports: [
    CommonModule,
    PagesRoutingModule,
    ReactiveFormsModule,
    KeyValuePipe,
    ButtonModule,
    DropdownModule,
    CalendarModule,
    InputTextModule,
    InputTextareaModule,
  ]
})
export class PagesModule { }
