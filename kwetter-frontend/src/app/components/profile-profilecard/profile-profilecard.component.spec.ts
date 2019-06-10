import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileProfilecardComponent } from './profile-profilecard.component';

describe('ProfileProfilecardComponent', () => {
  let component: ProfileProfilecardComponent;
  let fixture: ComponentFixture<ProfileProfilecardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileProfilecardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileProfilecardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
