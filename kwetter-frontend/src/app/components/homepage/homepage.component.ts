import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user/user.service';
import { User } from '../../models/user';
import { StorageService } from 'src/app/services/storage/storage.service';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  private currentUser: User;

  constructor(private storageService: StorageService, private userService: UserService, private authService: AuthenticationService, private router: Router) {
    if (authService.isLoggedIn())
      this.getCurrentUser();
    else 
      this.router.navigateByUrl('account/login');
  }

  ngOnInit() {
  }

  getCurrentUser() {
    this.userService.getUserByUUID(this.storageService.getItem('kwetter_uuid')).subscribe(response => {
      this.userService.setCurrentUser(response);
      this.currentUser = response;
    });
  }

}
