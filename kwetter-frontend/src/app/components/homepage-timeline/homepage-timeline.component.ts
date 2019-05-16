import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'homepage-timeline',
  templateUrl: './homepage-timeline.component.html',
  styleUrls: ['./homepage-timeline.component.css']
})
export class HomepageTimelineComponent implements OnInit {
  @Input() private currentUser: User;

  constructor() { }

  ngOnInit() {
  }

}
