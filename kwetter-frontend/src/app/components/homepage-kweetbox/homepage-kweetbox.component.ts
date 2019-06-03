import { Component, OnInit, Input, Output } from '@angular/core';
import { User } from 'src/app/models/user';
import { Kweet } from 'src/app/models/kweet';
import * as $ from 'jquery';
import * as moment from 'moment';
import { KweetService } from 'src/app/services/kweet/kweet.service';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'homepage-kweetbox',
  templateUrl: './homepage-kweetbox.component.html',
  styleUrls: ['./homepage-kweetbox.component.css']
})
export class HomepageKweetboxComponent implements OnInit {
  @Input() private currentUser: User;
  @Output() private userChanged: EventEmitter<User> = new EventEmitter();

  constructor(private kweetService: KweetService) { }

  ngOnInit() {
  }

  postKweet() {
    const message = $('#kweetInput').val();
    const kweet = new Kweet(message, moment().unix(), this.currentUser);
    this.kweetService.postKweet(kweet).subscribe(kweet => {
      this.currentUser.kweets.unshift(kweet);
      this.userChanged.emit(this.currentUser);
    });
    $('#kweetInput').empty();
  }
}
