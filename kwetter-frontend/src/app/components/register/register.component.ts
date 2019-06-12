import { Component, OnInit } from "@angular/core";
import { User } from "src/app/models/user";
import { NgForm, FormGroup, FormControl, Validators } from "@angular/forms";
import { AuthenticationService } from "src/app/services/authentication/authentication.service";
import { Router } from "@angular/router";
import { RecaptchaService } from "src/app/services/recaptcha/recaptcha.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  private user: User;
  private registerForm: FormGroup;
  private recaptchaSuccess: boolean;
  constructor(
    private authService: AuthenticationService,
    private recaptchaService: RecaptchaService,
    private router: Router
  ) {}

  ngOnInit() {
    this.registerForm = new FormGroup({
      username: new FormControl("", [
        Validators.required,
        Validators.minLength(5)
      ]),
      email: new FormControl("", [
        Validators.required,
        Validators.minLength(5)
      ]),
      location: new FormControl(""),
      website: new FormControl(""),
      bio: new FormControl(""),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(5)
      ])
    });
  }

  get f() {
    return this.registerForm.controls;
  }

  register() {
    console.log(this.registerForm);
    if (this.registerForm.invalid) {
      console.error("Form is invalid!");
      return;
    } 
    // else if (!this.recaptchaSuccess) {
    //   console.error("Cannot register without successful recaptcha!");
    // } 
    else {
      this.user = new User(
        this.f.username.value,
        this.f.email.value,
        this.f.location.value,
        this.f.website.value,
        this.f.bio.value,
        this.f.password.value
      );
      this.authService.register(this.user).subscribe(response => {
        this.router.navigateByUrl("");
      });
    }
  }

  resolved(token: any) {
    this.recaptchaService.validateRecaptcha(token).subscribe(response => {
      this.recaptchaSuccess = response["success"];
    });
  }
}
