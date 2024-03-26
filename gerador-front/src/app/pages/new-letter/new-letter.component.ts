import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { EnumDocumentType } from 'src/app/core/common/enums/enum.document.type';

@Component({
  selector: 'app-new-letter',
  templateUrl: './new-letter.component.html',
  styleUrls: ['./new-letter.component.scss'],
})
export class NewLetterComponent {
  public newDocumentForm: FormGroup;
  public documentTypeEnum = EnumDocumentType;

  constructor() {
    this.newDocumentForm = new FormGroup({
      documentType: new FormControl(),
      selectedDate: new FormControl(new Date()),
      from: new FormControl(),
      fromPosition: new FormControl(),
      to: new FormControl(),
      toPosition: new FormControl(),
      subject: new FormControl()
    });
  }
}
