import { StorageService } from './../../core/services/storage.service';
import { LoaderService } from './../../core/services/loader.service';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { EnumStorageType } from 'src/app/core/common/enums/enum.storage.type';
import { IResponseLogin } from 'src/app/core/models/IResponseLogin';
import { Router } from '@angular/router';
import { AuthAPI } from 'src/app/core/api/auth.api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  public hide = true;
  public loginForm: FormGroup;
  public errorMessage?: string;

  constructor(
    private authAPI: AuthAPI,
    private loaderService: LoaderService,
    private storageService: StorageService,
    private router: Router
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
  
  public login(): void {
    this.loaderService.setLoading(true);
  
    this.authAPI.login(this.loginForm.value).subscribe(
      
      (response: IResponseLogin) => {
        const storageType = this.loginForm.get('isRememberEnabled')?.value
        ? EnumStorageType.LOCAL
        : EnumStorageType.SESSION;
        this.storageService.setItem('token', response.token, storageType);
        this.router.navigate(['/pages']);
      },
      (error) => {
        this.errorMessage = 
          error.error && error.error.data && error.error.message
            ? error.error.data.message
            : error.error && error.error.message
            ? error.error.message
            : 'Ocorreu um erro na comunicação com o servidor. Tente novamente.';
      },
      () => {
        this.loaderService.setLoading(false);
      }
    );
  }
}
