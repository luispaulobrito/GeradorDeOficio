import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { PasswordForgotComponent } from './password-forgot/password-forgot.component';
import { NewPasswordComponent } from './new-password/new-password.component';
import { PasswordErrorsComponent } from '../theme/components/password-errors/password-errors.component';

@NgModule({
  declarations: [
    LoginComponent,
    PasswordForgotComponent,
    NewPasswordComponent,
    PasswordErrorsComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCheckboxModule,
    MatIconModule,
    MatButtonModule,
  ],
})
export class AuthModule { }
