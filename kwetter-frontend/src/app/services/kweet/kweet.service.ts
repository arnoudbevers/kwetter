import { Injectable } from "@angular/core";
import { ApiService } from "../api/api.service";
import { HttpClient } from "@angular/common/http";
import { Kweet } from "src/app/models/kweet";
import { map } from "rxjs/operators";
import { BehaviorSubject, Observable, of, Subject } from "rxjs";
import { StorageService } from "../storage/storage.service";

@Injectable({
  providedIn: "root"
})
export class KweetService extends ApiService {
  private timeline = [];

  constructor(http: HttpClient, private storageService: StorageService) {
    super(http);

    // this.dataStore = { timeline: [] };
    // this._timeline = <BehaviorSubject<Kweet[]>>new BehaviorSubject([]);
    // this.timeline = this._timeline.asObservable();
  }

  getAll(uuid: string) {
    return this.getHttpClient()
      .get<Kweet[]>(`${this.getApiUrl()}/users/${uuid}/kweets`)
      .subscribe(data => (this.timeline = data));
  }

  postKweet(kweet: Kweet) {
    this.getHttpClient()
      .post<Kweet>(`${this.getApiUrl()}/kweets`, kweet, this.getHttpOptions())
      .subscribe(data => {
        this.timeline.push(data as Kweet);
      });
  }
}
