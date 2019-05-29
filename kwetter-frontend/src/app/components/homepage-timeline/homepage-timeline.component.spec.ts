import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageTimelineComponent } from './homepage-timeline.component';

describe('HomepageTimelineComponent', () => {
  let component: HomepageTimelineComponent;
  let fixture: ComponentFixture<HomepageTimelineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageTimelineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageTimelineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
