import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileFriendshipsComponent } from './profile-friendships.component';

describe('ProfileFriendshipsComponent', () => {
  let component: ProfileFriendshipsComponent;
  let fixture: ComponentFixture<ProfileFriendshipsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileFriendshipsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileFriendshipsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
