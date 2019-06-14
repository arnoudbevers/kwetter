import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "src/app/services/authentication/authentication.service";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";

@Component({
  selector: "app-verify",
  templateUrl: "./verify.component.html",
  styleUrls: ["./verify.component.css"]
})
export class VerifyComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthenticationService,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.authService
      .verify(this.route.snapshot.paramMap.get("uuid"))
      .subscribe(response => {
        if (response["status"] == "OK")
          this.toastr.success(
            "You have been successfully verified!",
            "Verification"
          );
        else
          this.toastr.error(
            "The user you are trying to verify is invalid!",
            "Verification"
          );
        this.router.navigateByUrl("");
      });
  }
}
