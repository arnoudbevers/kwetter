import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';

import * as $ from 'jquery';
import { Credentials } from 'src/app/models/credentials';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import { StorageService } from 'src/app/services/storage/storage.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthenticationService, private storageService: StorageService, private router: Router) { }

  ngOnInit() {
  }

  login() {
    const credentials = new Credentials();
    credentials.username = $('#input_username').val();
    credentials.password = $('#input_password').val();
    this.authService.login(credentials).subscribe(response => {
      this.storageService.setItem('kwetter_jwt_token', response['jwt_token']);
      this.storageService.setItem('kwetter_uuid', response['uuid']);
      this.router.navigateByUrl('');
    });
  }

  onSubmit(f: NgForm){
    console.log('i submitted');
  }
}
