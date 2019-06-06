import { Component, OnInit, Input } from "@angular/core";
import { User } from "src/app/models/user";
import { UserService } from "src/app/services/user/user.service";
import { Friendship } from 'src/app/models/friendship';
import { FriendshipService } from 'src/app/services/friendship/friendship.service';

@Component({
  selector: "profile-details",
  templateUrl: "./profile-details.component.html",
  styleUrls: ["./profile-details.component.css"]
})
export class ProfileDetailsComponent implements OnInit {
  @Input() private profileUser: User;

  constructor(private userService: UserService, private friendshipService: FriendshipService) {}

  ngOnInit() {}

  follow() {
    const friendship = new Friendship(
      this.userService.getCurrentUser().uuid,
      this.profileUser.uuid
    );
    this.friendshipService.createFriendship(friendship).subscribe(response => {
      location.reload();
    });
  }

  unfollow() {
    const friendship = new Friendship(
      this.userService.getCurrentUser().uuid,
      this.profileUser.uuid
    );
    this.friendshipService.destroyFriendship(friendship).subscribe(response => {
      location.reload();
    });
  }

  checkIfFollows(user: User) {
    if(this.userService.getCurrentUser().following.some(u => u.uuid == user.uuid)) {
      return true;
    } else {
      return false;
    }
  }
}
