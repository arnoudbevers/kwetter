import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

import * as $ from 'jquery';
import { Credentials } from 'src/app/models/credentials';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthenticationService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    const credentials = new Credentials();
    credentials.username = $('#input_username').val();
    credentials.password = $('#input_password').val();
    this.authService.login(credentials).subscribe(response => {
      localStorage.setItem('kwetter_jwt_token', response['jwt_token']);
      this.router.navigateByUrl('');
    });
  }
}
