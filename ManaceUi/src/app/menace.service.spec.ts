import { TestBed } from '@angular/core/testing';

import { MenaceService } from './menace.service';

describe('MenaceService', () => {
  let service: MenaceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MenaceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
