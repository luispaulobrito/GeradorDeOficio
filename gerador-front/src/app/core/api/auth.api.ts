import { StorageService } from './../services/storage.service';
import { Injectable } from '@angular/core';
import { BaseAPI } from './base.api';
import { HttpClient } from '@angular/common/http';
import { FormGroup } from '@angular/forms';
import { IResponseLogin } from '../models/IResponseLogin';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthAPI extends BaseAPI {
  constructor(
    protected override httpClient: HttpClient,
    protected override storageService: StorageService
  ) {
    super(httpClient, storageService);
    this.apiUrl += '/auth/login';
  }

  /**
   * login
   *
   * Realiza o login
   *
   * @param data - Objeto com os dados para autenticação
   *
   * @returns Resposta da autenticação
   */
  public login(loginForm: FormGroup): Observable<IResponseLogin> {
    return this.post(loginForm);
  }
}
