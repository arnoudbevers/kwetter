import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Kwetter';
  constructor(authService: AuthenticationService, router: Router) {
    if (!authService.isLoggedIn()) {
      router.navigate(['account/login']);
    }
  }
}
