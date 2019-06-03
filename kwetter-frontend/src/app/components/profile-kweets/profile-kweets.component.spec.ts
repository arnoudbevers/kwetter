import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileKweetsComponent } from './profile-kweets.component';

describe('ProfileKweetsComponent', () => {
  let component: ProfileKweetsComponent;
  let fixture: ComponentFixture<ProfileKweetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileKweetsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileKweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
