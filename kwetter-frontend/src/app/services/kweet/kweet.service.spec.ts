import { TestBed } from '@angular/core/testing';

import { KweetService } from './kweet.service';

describe('KweetService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KweetService = TestBed.get(KweetService);
    expect(service).toBeTruthy();
  });
});
