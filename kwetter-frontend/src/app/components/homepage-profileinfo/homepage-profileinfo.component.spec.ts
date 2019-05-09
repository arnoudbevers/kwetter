import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageProfileinfoComponent } from './homepage-profileinfo.component';

describe('HomepageProfileinfoComponent', () => {
  let component: HomepageProfileinfoComponent;
  let fixture: ComponentFixture<HomepageProfileinfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageProfileinfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageProfileinfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
