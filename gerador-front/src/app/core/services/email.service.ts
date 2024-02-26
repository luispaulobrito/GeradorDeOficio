import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  private email = '';

  /**
   * Define o endereço de e-mail armazenado no serviço.
   *
   * @param email O endereço de e-mail a ser armazenado.
   */
  public setEmail(email: string): void {
    this.email = email;
  }

  /**
   * Obtém o endereço de e-mail armazenado no serviço.
   *
   * @returns O endereço de e-mail armazenado.
   */
  public getEmail(): string {
    return this.email;
  }
}
