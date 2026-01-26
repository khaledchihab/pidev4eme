import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardCandidatComponent } from './board-candidat.component';

describe('BoardCandidatComponent', () => {
  let component: BoardCandidatComponent;
  let fixture: ComponentFixture<BoardCandidatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardCandidatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoardCandidatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
