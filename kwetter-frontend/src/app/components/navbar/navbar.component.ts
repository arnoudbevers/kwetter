import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  logOut() {
    localStorage.removeItem('kwetter_jwt_token');
    localStorage.removeItem('kwetter_uuid');
    this.router.navigateByUrl('/account/login');
  }
}
