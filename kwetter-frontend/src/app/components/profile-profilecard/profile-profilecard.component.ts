import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'profile-profilecard',
  templateUrl: './profile-profilecard.component.html',
  styleUrls: ['./profile-profilecard.component.css']
})
export class ProfileProfilecardComponent implements OnInit {

  @Input() private profileUser: User;
  constructor() { }

  ngOnInit() {
  }

}
