import { Component, OnInit, Input } from "@angular/core";
import { User } from "src/app/models/user";
import { KweetService } from "src/app/services/kweet/kweet.service";
import { StorageService } from "src/app/services/storage/storage.service";

import * as moment from "moment";

@Component({
  selector: "homepage-timeline",
  templateUrl: "./homepage-timeline.component.html",
  styleUrls: ["./homepage-timeline.component.css"]
})
export class HomepageTimelineComponent implements OnInit {
  @Input() private currentUser: User;

  constructor(
    private kweetService: KweetService,
    private storageService: StorageService
  ) { }

  ngOnInit() {
    this.kweetService
      .getTimeline(this.storageService.getItem("kwetter_uuid"));
  }
}
