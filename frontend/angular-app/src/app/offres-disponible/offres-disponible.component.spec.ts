import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OffresDisponibleComponent } from './offres-disponible.component';

describe('OffresDisponibleComponent', () => {
  let component: OffresDisponibleComponent;
  let fixture: ComponentFixture<OffresDisponibleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OffresDisponibleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OffresDisponibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
