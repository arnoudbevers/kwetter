import { Component, OnInit, Input } from "@angular/core";
import { User } from "src/app/models/user";
import { UserService } from "src/app/services/user/user.service";
import { StorageService } from "src/app/services/storage/storage.service";

@Component({
  selector: "homepage-profileinfo",
  templateUrl: "./homepage-profileinfo.component.html",
  styleUrls: ["./homepage-profileinfo.component.css"]
})
export class HomepageProfileinfoComponent implements OnInit {
  @Input() private currentUser: User;

  constructor(
    private userService: UserService,
    private storageService: StorageService
  ) {
  }

  ngOnInit() { }
}
