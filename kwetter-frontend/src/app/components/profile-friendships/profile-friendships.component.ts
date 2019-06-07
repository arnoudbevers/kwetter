import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'profile-friendships',
  templateUrl: './profile-friendships.component.html',
  styleUrls: ['./profile-friendships.component.css']
})
export class ProfileFriendshipsComponent implements OnInit {
  @Input() private profileUser: User;

  constructor() { }

  ngOnInit() {
  }

}
