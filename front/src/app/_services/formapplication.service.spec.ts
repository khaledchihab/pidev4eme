import { TestBed } from '@angular/core/testing';

import { FormapplicationService } from './formapplication.service';

describe('FormapplicationService', () => {
  let service: FormapplicationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormapplicationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
