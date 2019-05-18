import { Component, OnInit, Input, OnDestroy } from "@angular/core";
import { User } from "src/app/models/user";
import { Kweet } from "src/app/models/kweet";
import { KweetService } from "src/app/services/kweet/kweet.service";
import { Observable, Subscription, BehaviorSubject } from "rxjs";
import { StorageService } from "src/app/services/storage/storage.service";

@Component({
  selector: "homepage-timeline",
  templateUrl: "./homepage-timeline.component.html",
  styleUrls: ["./homepage-timeline.component.css"]
})
export class HomepageTimelineComponent implements OnInit {
  @Input() private currentUser: User;
  // private _timeline: BehaviorSubject<Kweet[]> = new BehaviorSubject([]);
  // // private readonly timeline =  this._timeline.asObservable();

  constructor(
    private kweetService: KweetService,
    private storageService: StorageService
  ) {
    const timeline = new Observable((observer) => {
      const {next, error} = observer;
    })
  }

  get timeline() {
    // return this._timeline.asObservable();
  }

  ngOnInit() {}

  loadInitialData() {
  }
}
