import { Injectable } from "@angular/core";
import { ApiService } from "../api/api.service";
import { HttpClient } from "@angular/common/http";
import { User } from "../../models/user";
import { StorageService } from "../storage/storage.service";
import { map } from "rxjs/operators";

@Injectable({
  providedIn: "root"
})
export class UserService extends ApiService {

  private _currentUser: User;

  constructor(http: HttpClient, private storageService: StorageService) {
    super(http);
    this.getUserByUUID(this.storageService.getItem('kwetter_uuid')).subscribe(data => {
      this._currentUser = data;
    });
  }

  getCurrentUser() {
    return this._currentUser;
  }

  setCurrentUser(user: User) {
    this._currentUser = user;
  }

  getUserByUUID(uuid: String) {
    return this.getHttpClient()
      .get<User>(`${this.getApiUrl()}/users/${uuid}`, this.getHttpOptions())
      .pipe(
        map(user => {
          return user;
        })
      );
  }

  getUserByUsername(username: String) {
    return this.getHttpClient()
      .get<User>(`${this.getApiUrl()}/users/search/${username}`, this.getHttpOptions())
      .pipe(
        map(user => {
          return user;
        })
      );
  }

  searchByUsername(searchString: string) {
    return this.getHttpClient()
      .get<User[]>(`${this.getApiUrl()}/users/searchquery/${searchString}`, this.getHttpOptions())
      .pipe(
        map(users => {
          return users;
        })
      );
  }
}
