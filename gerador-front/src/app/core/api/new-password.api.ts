import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IRequestNewPass } from '../models/IRequestNewPass';
import { StorageService } from '../services/storage.service';
import { BaseAPI } from './base.api';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class NewPasswordApi extends BaseAPI {
  constructor(
    protected override httpClient: HttpClient,
    protected override storageService: StorageService
  ) {
    super(httpClient, storageService);
    this.apiUrl += '/password-reset/reset';
  }

  /**
   * Faz o POST na rota /password-reset/reset
   * @param userData Json contendo as senhas para fazer o POST na rota reset/password
   * @returns Um observable contendo a resposta da rota
   */
  public newPassRequest(userData: IRequestNewPass): Observable<string> {
    return this.post(userData);
  }

  /**
   * Verifica através de um GET na rota /password-reset/reset/:id/:resetToekn
   * se o id e o resetToken sao validos
   * @param id ID do usuário a ser verificado
   * @param resetToken Token a ser verificado
   * @returns Um observable contendo a resposta da rota
   */
  public tokenVerify(id: string, resetToken: string): Observable<string> {
    return this.get(`/${id}/${resetToken}`);
  }
}