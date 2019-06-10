import { Component, OnInit, Input, Output } from "@angular/core";
import { User } from "src/app/models/user";
import { Kweet } from "src/app/models/kweet";
import * as $ from "jquery";
import * as moment from "moment";
import { KweetService } from "src/app/services/kweet/kweet.service";
import { EventEmitter } from "@angular/core";
import { SocketService } from "src/app/services/socket/socket.service";
import { environment } from "src/environments/environment";

@Component({
  selector: "homepage-kweetbox",
  templateUrl: "./homepage-kweetbox.component.html",
  styleUrls: ["./homepage-kweetbox.component.css"]
})
export class HomepageKweetboxComponent implements OnInit {
  @Input() private currentUser: User;
  @Output() private userChanged: EventEmitter<User> = new EventEmitter();
  socket;
  constructor(
    private kweetService: KweetService,
    private socketService: SocketService
  ) {
    this.socket = this.socketService.open(environment.socketDistUrl);
  }

  ngOnInit() {}

  postKweet() {
    const message = $("#kweetInput").val();
    const kweet = new Kweet(message, moment().unix(), this.currentUser);
    this.kweetService.postKweet(kweet).subscribe(kweet => {
      this.currentUser.kweets.unshift(kweet);
      this.userChanged.emit(this.currentUser);
      this.socket.send(JSON.stringify(kweet));
    });
    $("#kweetInput").empty();
  }
}
