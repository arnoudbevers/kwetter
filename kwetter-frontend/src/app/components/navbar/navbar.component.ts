import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "src/app/services/authentication/authentication.service";
import { StorageService } from "src/app/services/storage/storage.service";
import { UserService } from "src/app/services/user/user.service";
import { User } from "src/app/models/user";

@Component({
  selector: "navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  private currentUser: User;

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private storageService: StorageService,
    private userService: UserService
  ) {}

  ngOnInit() {
    if(this.isLoggedIn()) {
      this.userService.getUserByUUID(this.storageService.getItem('kwetter_uuid')).subscribe(data => {
        this.currentUser = data;
      });
    }
  }

  isLoggedIn() {
    if (this.authService.isLoggedIn()) {
      return true;
    }
    return false;
  }

  logOut() {
    this.storageService.removeItem("kwetter_jwt_token");
    this.storageService.removeItem("kwetter_uuid");
    this.router.navigateByUrl("/account/login");
  }
}
