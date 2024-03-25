import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoaderService } from 'src/app/core/services/loader.service';
import { StorageService } from 'src/app/core/services/storage.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss'],
})
export class MenuComponent {
  constructor(
    private storageService: StorageService,
    private router: Router,
    private loaderService: LoaderService
  ) {}

  public isMenuOpen = false;

  public toggleMenu(): void{
    this.isMenuOpen = !this.isMenuOpen;
  }

  /**
   * Efetua o logout do usuário, redirecionando para a página de login e removendo o token de autenticação.
   * Também lida com o indicador de carregamento.
   */
  public logout(): void {
    this.loaderService.setLoading(true);
    this.router.navigate(['auth/login']);
    this.storageService.removeItem('token');
    this.loaderService.setLoading(false);
  }
}
