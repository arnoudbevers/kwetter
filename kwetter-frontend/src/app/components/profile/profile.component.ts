import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user/user.service';
import { ActivatedRoute } from '@angular/router';
import { Friendship } from 'src/app/models/friendship';

@Component({
  selector: 'profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  private profileUser: User;
  constructor(private route: ActivatedRoute, private userService: UserService) {

  }

  ngOnInit() {
    this.route.params.subscribe(routeParams => {
      this.userService.getUserByUsername(this.route.snapshot.paramMap.get("username"))
        .subscribe(data => {
          this.profileUser = data;
        });
    });
  }

}
