import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormFieldManagerComponent } from './form-field-manager.component';

describe('FormFieldManagerComponent', () => {
  let component: FormFieldManagerComponent;
  let fixture: ComponentFixture<FormFieldManagerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormFieldManagerComponent]
    });
    fixture = TestBed.createComponent(FormFieldManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
