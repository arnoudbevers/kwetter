import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "src/app/services/authentication/authentication.service";
import { StorageService } from "src/app/services/storage/storage.service";
import { UserService } from "src/app/services/user/user.service";
import { User } from "src/app/models/user";
import { THROW_IF_NOT_FOUND } from '@angular/core/src/di/injector';

@Component({
  selector: "navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit {
  private currentUser: User;
  private searchString: string;
  private searchResults: User[] = [];
  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private storageService: StorageService,
    private userService: UserService
  ) { }

  ngOnInit() {
    if (this.isLoggedIn()) {
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

  onSearchValueChanged(event: any) {
    if (this.searchResults.some(u => u.username == this.searchString)) { // Check if searchResults contains
      this.router.navigateByUrl(`/user/${this.searchString}`);
    }
    else if (this.searchString.length > 3) {
      this.userService.searchByUsername(this.searchString)
        .subscribe(results => this.searchResults = results);
    }
    this.searchResults = [];
  }
}
