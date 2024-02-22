import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { InitialContentComponent } from './initial-content/initial-content.component';
import { NewLetterComponent } from './new-letter/new-letter.component';
import { ListLetterComponent } from './list-letter/list-letter.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    children: [
      { path: 'home', component: InitialContentComponent },
      { path: 'new-letter', component: NewLetterComponent },
      { path: 'list-letter', component: ListLetterComponent },
    ]
  },
  { path: '**', redirectTo: 'home', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {}
