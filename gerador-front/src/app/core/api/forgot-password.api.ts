import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../services/storage.service';
import { BaseAPI } from './base.api';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ForgotPasswordAPI extends BaseAPI {
  constructor(
    protected override httpClient: HttpClient,
    protected override storageService: StorageService
  ) {
    super(httpClient, storageService);
    this.apiUrl += '/password-reset/forgot';
  }

  /**
   * Envia uma solicitação para redefinir a senha associada ao endereço de e-mail fornecido.
   *
   * @param emailForm O formulário contendo o endereço de e-mail para o qual a redefinição de senha será solicitada.
   * @returns Um Observable<boolean> que representa a resposta da solicitação de redefinição de senha.
   */
  public passwordReset(emailForm: FormGroup): Observable<boolean> {
    return this.post({ login: emailForm });
  }
}
