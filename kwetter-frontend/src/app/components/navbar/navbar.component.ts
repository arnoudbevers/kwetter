import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { StorageService } from 'src/app/services/storage/storage.service';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router, private authService: AuthenticationService, private storageService: StorageService) { }

  ngOnInit() {
    console.log(this.isLoggedIn())
  }

  isLoggedIn() {
    if (this.authService.isLoggedIn()) {
      return true;
    }
    return false;
  }

  logOut() {
    this.storageService.removeItem('kwetter_jwt_token');
    this.storageService.removeItem('kwetter_uuid');
    this.router.navigateByUrl('/account/login');
  }
}
