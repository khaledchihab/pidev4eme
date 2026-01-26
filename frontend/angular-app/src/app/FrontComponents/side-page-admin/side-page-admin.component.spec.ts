import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidePageAdminComponent } from './side-page-admin.component';

describe('SidePageAdminComponent', () => {
  let component: SidePageAdminComponent;
  let fixture: ComponentFixture<SidePageAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidePageAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidePageAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
