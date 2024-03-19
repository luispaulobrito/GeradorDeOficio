import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PasswordErrorsComponent } from './password-errors.component';

describe('PasswordErrorsComponent', () => {
  let component: PasswordErrorsComponent;
  let fixture: ComponentFixture<PasswordErrorsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PasswordErrorsComponent]
    });
    fixture = TestBed.createComponent(PasswordErrorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
