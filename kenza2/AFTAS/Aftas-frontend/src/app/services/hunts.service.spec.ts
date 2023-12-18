import { TestBed } from '@angular/core/testing';

import { HuntsService } from './hunts.service';

describe('HuntsService', () => {
  let service: HuntsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HuntsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
