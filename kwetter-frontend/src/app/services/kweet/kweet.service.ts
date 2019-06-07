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
  }

  getAll(uuid: string) {
    return this.getHttpClient().get<Kweet[]>(
      `${this.getApiUrl()}/users/${uuid}/kweets`
    );
  }

  getTimeline(uuid: string) {
    return this.getHttpClient()
      .get<Kweet[]>(`${this.getApiUrl()}/users/${uuid}/timeline`)
      .pipe(map(data => data));
  }

  postKweet(kweet: Kweet) {
    return this.getHttpClient()
      .post<Kweet>(`${this.getApiUrl()}/kweets`, kweet, this.getHttpOptions())
      .pipe(
        map(data => {
          this.timeline.unshift(data as Kweet);
          return data;
        })
      );
  }
}
