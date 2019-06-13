import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-verify',
  templateUrl: './verify.component.html',
  styleUrls: ['./verify.component.css']
})
export class VerifyComponent implements OnInit {

  constructor(private route: ActivatedRoute, private authService: AuthenticationService) { }

  ngOnInit() {
    this.authService.verify(this.route.snapshot.paramMap.get("uuid")).subscribe(response => console.log(response));
  }

}
