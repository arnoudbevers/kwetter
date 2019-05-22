import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from '../../models/user';
import { StorageService } from 'src/app/services/storage/storage.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  private currentUser: User;

  constructor(private storageService: StorageService, private userService: UserService, private authService: AuthenticationService) {
    if (authService.isLoggedIn())
      this.getCurrentUser();
  }

  ngOnInit() {
  }

  getCurrentUser() {
    this.userService.getUser(this.storageService.getItem('kwetter_uuid')).subscribe(response => {
      this.userService.setCurrentUser(response);
      this.currentUser = response;
    });
  }

}
