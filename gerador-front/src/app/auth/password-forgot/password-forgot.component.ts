import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EmailService } from 'src/app/core/services/email.service';
import { LoaderService } from 'src/app/core/services/loader.service';

@Component({
  selector: 'app-password-forgot',
  templateUrl: './password-forgot.component.html',
  styleUrls: ['./password-forgot.component.scss'],
})
export class PasswordForgotComponent implements OnInit{
  public emailForm: FormGroup;
  public login!: string;

  constructor(
    // private forgotPasswordAPI: ForgotPasswordAPI,
    private router: Router,
    private loaderService: LoaderService,
    // private modalService: ModalService,
    private emailService: EmailService
  ) {
    this.emailForm = new FormGroup({
      login: new FormControl('', [Validators.required, Validators.pattern(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/)]),
    });
  }

  public ngOnInit(): void {
    this.initializeForm();
  }

  /**
   * Inicializa o formulário, preenchendo o campo de e-mail com o valor armazenado no serviço EmailService.
   */
  private initializeForm(): void {
    const storedEmail = this.emailService.getEmail();
    if (storedEmail) {
      this.emailForm.patchValue({ login: storedEmail });
    }
  }

  onSubmit(){}
}
