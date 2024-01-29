import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { finalize } from 'rxjs';
import { ForgotPasswordAPI } from 'src/app/core/api/forgot-password.api';
import { EmailService } from 'src/app/core/services/email.service';
import { LoaderService } from 'src/app/core/services/loader.service';
import { ModalService } from 'src/app/core/services/modal.service';

@Component({
  selector: 'app-password-forgot',
  templateUrl: './password-forgot.component.html',
  styleUrls: ['./password-forgot.component.scss'],
})
export class PasswordForgotComponent implements OnInit {
  public emailForm: FormGroup;
  public login!: string;

  constructor(
    private forgotPasswordAPI: ForgotPasswordAPI,
    private loaderService: LoaderService,
    private modalService: ModalService,
    private emailService: EmailService
  ) {
    this.emailForm = new FormGroup({
      login: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/),
      ]),
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

  /**
   * Manipula o envio do formulário de redefinição de senha.
   * Este método chama a API de redefinição de senha e exibe um diálogo de sucesso ou captura erros.
   */
  public onSubmit(): void {
    if (this.emailForm.invalid) {
      return;
    }

    this.loaderService.setLoading(true);
    
    this.forgotPasswordAPI
      .passwordReset(this.emailForm.value.login)
      .pipe(
        finalize(() => {
          this.loaderService.setLoading(false);
        })
      )
      .subscribe(
        () => {
          this.modalService.showDialog({
            title: 'Sucesso',
            message: 'Seu e-mail cadastrado receberá em breve instruções para redefinir a senha.',
            feedback: 'success',
          });
        },
        () => {
          this.modalService.showDialog({
            title: 'Falha!',
            message: 'Ocorreu um erro na comunicação com o servidor. Tente novamente.',
            feedback: 'error',
          });
        }
      );
  }
}
