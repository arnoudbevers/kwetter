import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageKweetboxComponent } from './homepage-kweetbox.component';

describe('HomepageKweetboxComponent', () => {
  let component: HomepageKweetboxComponent;
  let fixture: ComponentFixture<HomepageKweetboxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageKweetboxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageKweetboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
