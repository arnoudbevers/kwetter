import { Injectable } from '@angular/core';
import { ApiService } from '../api/api.service';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService extends ApiService {

  private _currentUser: User;

  constructor(http: HttpClient) {
    super(http);
  }

  getUser(uuid: String) {
    return this.getHttpClient().get<User>(`${this.getApiUrl()}/users/${uuid}`, this.getHttpOptions());
  }


  getCurrentUser() {
    return this._currentUser;
  }
  
  // TODO: null check
  setCurrentUser(user: User) {
    this._currentUser = user;
  } 
}
