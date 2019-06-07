import { Injectable } from "@angular/core";
import { ApiService } from "../api/api.service";
import { HttpClient } from "@angular/common/http";
import { Friendship } from 'src/app/models/friendship';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: "root"
})
export class FriendshipService extends ApiService {
  
  constructor(http: HttpClient) {
    super(http);
  }

  createFriendship(friendship: Friendship) {
    return this.getHttpClient()
      .post(`${this.getApiUrl()}/friendships/create`, friendship, this.getHttpOptions())
      .pipe(
        map(response => {
          return response;
        })
      );
  }

  destroyFriendship(friendship: Friendship) {
    return this.getHttpClient()
      .post(`${this.getApiUrl()}/friendships/destroy`, friendship, this.getHttpOptions())
      .pipe(
        map(response => {
          return response;
        })
      );
  }
}
