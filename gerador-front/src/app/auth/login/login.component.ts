import { StorageService } from './../../core/services/storage.service';
import { LoaderService } from './../../core/services/loader.service';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EnumStorageType } from 'src/app/core/common/enums/enum.storage.type';
import { IResponseLogin } from 'src/app/core/models/IResponseLogin';
import { Router } from '@angular/router';
import { AuthAPI } from 'src/app/core/api/auth.api';
import { finalize } from 'rxjs';
import { EmailService } from 'src/app/core/services/email.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  public hide = true;
  public loginForm: FormGroup;
  public errorMessage?: string;

  constructor(
    private authAPI: AuthAPI,
    private loaderService: LoaderService,
    private storageService: StorageService,
    private router: Router,
    private emailService: EmailService,
  ) {
    this.loginForm = new FormGroup({
      login: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/),
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
      ]),
      isRememberEnabled: new FormControl(false),
    });
  }

  /**
   * Realiza o processo de login.
   * - Define o token de acordo com a opção de "Lembrar de mim".
   * - Navega para 'pages' em caso de sucesso.
   * - Exibe uma mensagem de erro em caso de falha.
   */
  public login(): void {
    if (this.loginForm.invalid) {
      return;
    }
    
    this.loaderService.setLoading(true);
    this.storageService.removeItem('token');  
    this.authAPI
      .login(this.loginForm.value)
      .pipe(
        finalize(() => {
          this.loaderService.setLoading(false);
        })
      )
      .subscribe(
        (response: IResponseLogin) => {
          const storageType = this.loginForm.get('isRememberEnabled')?.value
            ? EnumStorageType.LOCAL
            : EnumStorageType.SESSION;
          this.storageService.setItem('token', response.token, storageType);
          this.router.navigate(['/pages/home']);
        },
        (error) => {
          this.errorMessage =
            error.error && error.error.message
              ? error.error.message
              : 'Ocorreu um erro na comunicação com o servidor. Tente novamente.';
        }
      );
  }

  public forgotPassword(): void {
    this.emailService.setEmail(this.loginForm.get('login')?.value);
    this.router.navigate(['/auth/password-forgot']);
  }
}
