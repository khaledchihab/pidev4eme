import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormfieldsComponent } from './formfields.component';

describe('FormfieldsComponent', () => {
  let component: FormfieldsComponent;
  let fixture: ComponentFixture<FormfieldsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormfieldsComponent]
    });
    fixture = TestBed.createComponent(FormfieldsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
