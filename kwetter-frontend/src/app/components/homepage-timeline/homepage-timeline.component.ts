import { Component, OnInit, Input } from "@angular/core";
import { User } from "src/app/models/user";
import { KweetService } from "src/app/services/kweet/kweet.service";
import { StorageService } from "src/app/services/storage/storage.service";
import { environment } from "src/environments/environment";

import * as moment from "moment";
import { SocketService } from "src/app/services/socket/socket.service";
import { Kweet } from 'src/app/models/kweet';

@Component({
  selector: "homepage-timeline",
  templateUrl: "./homepage-timeline.component.html",
  styleUrls: ["./homepage-timeline.component.css"]
})
export class HomepageTimelineComponent implements OnInit {
  @Input() private currentUser: User;
  socket;
  private timeline: Kweet[];

  constructor(
    private kweetService: KweetService,
    private storageService: StorageService,
    private socketService: SocketService
  ) {
    this.socket = this.socketService.open(environment.socketDistUrl);
    this.socket.messages.subscribe(message => {
      this.timeline.unshift(JSON.parse(message) as Kweet);
    });
  }

  ngOnInit() {
    // this.kweetService.getTimeline(this.storageService.getItem("kwetter_uuid"));
    this.getTimeline();
  }

  getTimeline() {
    this.kweetService.getTimeline(
      this.storageService.getItem("kwetter_uuid")
    ).subscribe(data => {
      this.timeline = data;
    });
  }
  convertDate(unix: number) {
    return moment.unix(unix).format("DD-MM-YYYY, HH:mm");
  }
}
