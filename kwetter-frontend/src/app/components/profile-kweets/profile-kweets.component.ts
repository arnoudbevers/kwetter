import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'profile-kweets',
  templateUrl: './profile-kweets.component.html',
  styleUrls: ['./profile-kweets.component.css']
})
export class ProfileKweetsComponent implements OnInit {
  @Input() private profileUser: User;
  constructor() { }

  ngOnInit() {
  }

}
