import { Injectable } from "@angular/core";
import { ApiService } from "../api/api.service";
import { HttpClient } from "@angular/common/http";
import { Kweet } from 'src/app/models/kweet';

@Injectable({
  providedIn: "root"
})
export class KweetService extends ApiService {
  constructor(http: HttpClient) {
    super(http);
  }

  postKweet(kweet: Kweet) {
    return this.getHttpClient().post(`${this.getApiUrl()}/kweets`, kweet, this.getHttpOptions());
  }
}
