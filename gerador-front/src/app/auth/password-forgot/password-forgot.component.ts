import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoaderService } from 'src/app/core/services/loader.service';

@Component({
  selector: 'app-password-forgot',
  templateUrl: './password-forgot.component.html',
  styleUrls: ['./password-forgot.component.scss'],
})
export class PasswordForgotComponent {
  public emailForm: FormGroup;
  public login!: string;

  constructor(
    // private forgotPasswordAPI: ForgotPasswordAPI,
    private router: Router,
    private loaderService: LoaderService,
    // private modalService: ModalService,
    // private emailService: EmailService
  ) {
    this.emailForm = new FormGroup({
      login: new FormControl('', [Validators.required, Validators.pattern(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/)]),
    });
  }

  onSubmit(){}
}
