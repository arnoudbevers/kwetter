import { Component, OnInit, Input, OnDestroy } from "@angular/core";
import { User } from "src/app/models/user";
import { Kweet } from "src/app/models/kweet";
import { KweetService } from "src/app/services/kweet/kweet.service";
import { Observable, Subscription, BehaviorSubject } from "rxjs";
import { StorageService } from "src/app/services/storage/storage.service";

import * as moment from "moment";

@Component({
  selector: "homepage-timeline",
  templateUrl: "./homepage-timeline.component.html",
  styleUrls: ["./homepage-timeline.component.css"]
})
export class HomepageTimelineComponent implements OnInit {
  @Input() private currentUser: User;
  public timeline = [];

  constructor(
    private kweetService: KweetService,
    private storageService: StorageService
  ) {}

  ngOnInit() {
    this.kweetService
      .getAll(this.storageService.getItem("kwetter_uuid"))
      .subscribe(data => {
        this.timeline = data;
        console.log(this.timeline);
      });
  }

  convertDate(unix: number) {
    return moment.unix(unix).utc().format("DD-MM-YYYY, HH:MM");
  }
}
