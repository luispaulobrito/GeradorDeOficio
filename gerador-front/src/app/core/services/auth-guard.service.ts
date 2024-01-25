import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private storageService: StorageService, private router: Router) {}

  /*
  Verifica se o usuário está autenticado antes de permitir o acesso a uma rota.
  Se não estiver, redireciona para tela de login 
  */
  public canActivate(): boolean | UrlTree | Observable<boolean | UrlTree> {
    if (this.storageService.getItem('token')) {
      return true;
    } else {
      this.router.navigate(['auth'], { replaceUrl: true });
      return false;
    }
  }
}
