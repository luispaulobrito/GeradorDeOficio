import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PagesRoutingModule } from './pages-routing.module';
import { HomeComponent } from './home/home.component';
import { MenuComponent } from '../theme/components/menu/menu.component';
import { FooterComponent } from '../theme/components/footer/footer.component';
import { NewLetterComponent } from './new-letter/new-letter.component';
import { InitialContentComponent } from './initial-content/initial-content.component';
import { ListLetterComponent } from './list-letter/list-letter.component';


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
  ]
})
export class PagesModule { }
