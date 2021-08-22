import { TestBed } from '@angular/core/testing';

import { RaiseIssueService } from './raise-issue.service';

describe('RaiseIssueService', () => {
  let service: RaiseIssueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RaiseIssueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
