import { Component, OnInit } from "@angular/core";
import { User } from "src/app/models/user";
import { NgForm, FormGroup, FormControl, Validators } from "@angular/forms";
import { AuthenticationService } from "src/app/services/authentication/authentication.service";
import { Router } from "@angular/router";
import { RecaptchaService } from "src/app/services/recaptcha/recaptcha.service";
import { ToastrService } from 'ngx-toastr';

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
    private toastr: ToastrService,
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
s
  get f() {
    return this.registerForm.controls;
  }

  register() {
    if (this.registerForm.invalid) {
      this.toastr.warning("Please fill in all the required fields!", "Form validation");
      return;
    } 
    else if (!this.recaptchaSuccess) {
      this.toastr.warning("reCAPTCHA", "Please validate using the reCAPTCHA before registering!");
      return;
    } 
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
      if(this.recaptchaSuccess){
        this.toastr.success("reCAPTCHA has been validated!", "reCAPTCHA");
      } else {
        this.toastr.error("Something went wrong when validating this reCAPTCHA.. Please reload the page!", "reCAPTCHA");
      }
    });
  }
}
