import { Injectable } from "@angular/core";
import { ApiService } from "../api/api.service";
import { HttpClient } from "@angular/common/http";
import { Credentials } from "src/app/models/credentials";
import { StorageService } from "../storage/storage.service";
import { tap, catchError, map } from "rxjs/operators";
import { User } from "src/app/models/user";

@Injectable({
  providedIn: "root"
})
export class AuthenticationService extends ApiService {
  constructor(http: HttpClient, private storageService: StorageService) {
    super(http);
  }

  isLoggedIn(): boolean {
    if (
      this.storageService.getItem("kwetter_jwt_token") == "" ||
      this.storageService.getItem("kwetter_jwt_token") == null
    ) {
      return false;
    }
    return true;
  }

  login(credentials: Credentials) {
    return this.getHttpClient()
      .post(
        `${this.getApiUrl()}/auth/login`,
        credentials,
        this.getHttpOptions()
      )
      .pipe(catchError(this.handleError));
  }

  register(user: User) {
    return this.getHttpClient()
      .post(`${this.getApiUrl()}/auth/register`, user, this.getHttpOptions())
      .pipe(
        map(user => user),
        catchError(this.handleError)
      );
  }

  verify(uuid: string) {
    return this.getHttpClient()
      .get(`${this.getApiUrl()}/auth/verify/${uuid}`, this.getHttpOptions())
      .pipe(
        map(response => response),
        catchError(this.handleError)
      );
  }
}
