import { Component, OnInit } from "@angular/core";
import { User } from "src/app/models/user";
import { NgForm } from "@angular/forms";
import { AuthenticationService } from "src/app/services/authentication/authentication.service";
import { Router } from '@angular/router';
import { RecaptchaService } from 'src/app/services/recaptcha/recaptcha.service';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  private user: User;

  constructor(private authService: AuthenticationService, private recaptchaService: RecaptchaService, private router: Router) {
    this.user = new User();
  }

  ngOnInit() {}

  register(form: NgForm) {
    this.user.username = form.value.username;
    this.user.email = form.value.email;
    this.user.location = form.value.location;
    this.user.bio = form.value.bio;
    this.user.password = form.value.password;
    this.authService.register(this.user).subscribe(response => {
      this.router.navigateByUrl('');
    });
  }

  resolved(token: any) {
    console.log(token);
    this.recaptchaService.validateRecaptcha(token).subscribe(response => console.log('wajow'));
  }
}
