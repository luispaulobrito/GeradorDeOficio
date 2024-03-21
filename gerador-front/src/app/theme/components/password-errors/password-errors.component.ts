import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-password-errors',
  templateUrl: './password-errors.component.html',
  styleUrls: ['./password-errors.component.scss'],
})
export class PasswordErrorsComponent {
  @Input() form!: FormGroup;
  @Input() errorKey!: string;
  @Input() errorMessage!: string;

}
